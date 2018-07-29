package co.proficiency.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.proficiency.app.R;
import co.proficiency.app.adapter.DataListAdapter;
import co.proficiency.app.exceptions.APIErrorHandler;
import co.proficiency.app.generic.IConstant;
import co.proficiency.app.model.APIErrorObj;
import co.proficiency.app.model.DataList;
import co.proficiency.app.model.ResponseData;
import co.proficiency.app.utils.GeneralUtil;
import co.proficiency.app.webservice.ApiInterface;
import co.proficiency.app.webservice.HttpRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    public static final String TAG = DashboardActivity.class.getSimpleName();

    Context mContext;
    Unbinder mUnbinder;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.dataListview)
    RecyclerView dataListView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    DataListAdapter dataListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        process();
    }

    private void init() {
        mUnbinder = ButterKnife.bind(this);
        mContext = DashboardActivity.this;
        setSupportActionBar(toolbar);

        dataListView.setHasFixedSize(true);
        dataListView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    private void process() {
        if (GeneralUtil.isInternetConnected(mContext)) {
            GetDataList();
        } else {
            GeneralUtil.ShowLongToast(mContext, getString(R.string.try_after_sometime));
            finish();
        }
    }

    @OnClick(R.id.btnRefresh)
    public void onClick() {
        process();
    }

    public void GetDataList() {
        GeneralUtil.showProgressDialog(mContext, getString(R.string.txt_progress_loading));
        ApiInterface apiService = HttpRequest.getInstance().create(ApiInterface.class);
        Call<ResponseData> call = apiService.getData();
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                try {
                    GeneralUtil.dismissProgressDialog();
                    Log.d("Code", "" + response.code());
                    int resCode = response.code();
                    if (resCode >= IConstant.SUCCESS_START && resCode < IConstant.SUCCESS_END) {
                        ResponseData responseData = response.body();
                        if (responseData != null) {
                            Log.d("data Message", "" + response.message());
                            Log.d("data Body", "" + response.body().toString());
                            String title = responseData.getTitle();
                            txtTitle.setText("");
                            if (!title.isEmpty()) {
                                txtTitle.setText(title);
                            }
                            ArrayList<DataList> dataList = (ArrayList<DataList>) responseData.getRows();
                            DataListAdapter dataListAdapter = new DataListAdapter(mContext, dataList);
                            dataListView.setAdapter(dataListAdapter);
                        } else {
                            APIErrorHandler.ConnectionErrorHandler(mContext, new APIErrorObj(resCode, response.message()));
                        }
                    } else {
                        APIErrorHandler.ConnectionErrorHandler(mContext, new APIErrorObj(resCode, response.message()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                GeneralUtil.dismissProgressDialog();
                if (t instanceof IOException) {
                    Log.d("Error", "Network Error");
                    APIErrorHandler.ConnectionErrorHandler(mContext, new APIErrorObj(IConstant.NETWORK_ERR, t.getMessage()));
                } else
                    t.printStackTrace();
            }
        });
    }
}
