package co.proficiency.app.exceptions;

import android.content.Context;

import co.proficiency.app.R;
import co.proficiency.app.generic.IConstant;
import co.proficiency.app.model.APIErrorObj;
import co.proficiency.app.utils.GeneralUtil;

public class APIErrorHandler {

    public static void ResponseErrorHandler(Context ctx, APIErrorObj exceptionObj) {
        try {
            int errorCode = exceptionObj.getErrorCode();
            String errorMsg = exceptionObj.getErrorMsg();
                GeneralUtil.ShowLongToast(ctx, errorMsg);
        } catch (Exception e) {
            e.printStackTrace();
            GeneralUtil.ShowLongToast(ctx, ctx.getString(R.string.alert_error));
        }
    }

    public static void ConnectionErrorHandler(Context ctx, APIErrorObj exceptionObj) {
        try {
            int errorCode = exceptionObj.getErrorCode();
            String errorMsg = exceptionObj.getErrorMsg();
            if (errorCode == IConstant.NETWORK_ERR) {
                errorMsg = ctx.getString(R.string.err_network);
            } else if (errorCode == IConstant.UNAUTHORIZED_ERR) {
                errorMsg = ctx.getString(R.string.err_Unauthentication);
            } else if (errorCode >= IConstant.CLIENT_ERR_START && errorCode < IConstant.CLIENT_ERR_END) {
                errorMsg = ctx.getString(R.string.err_client);
            } else if (errorCode >= IConstant.SERVER_ERR_START && errorCode < IConstant.SERVER_ERR_END) {
                errorMsg = ctx.getString(R.string.err_server);
            } else {
                errorMsg = ctx.getString(R.string.err_unexpected);
            }
            GeneralUtil.ShowLongToast(ctx, errorMsg);
        } catch (Exception e) {
            e.printStackTrace();
            GeneralUtil.ShowLongToast(ctx, ctx.getString(R.string.alert_error));
        }
    }
}