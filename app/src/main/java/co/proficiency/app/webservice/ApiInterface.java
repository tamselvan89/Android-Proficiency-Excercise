package co.proficiency.app.webservice;

import com.google.gson.JsonObject;

import co.proficiency.app.generic.IConstant;
import co.proficiency.app.model.ResponseData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    /*GetData*/
    @GET(IConstant.Data_API)
    Call<ResponseData> getData();
}
