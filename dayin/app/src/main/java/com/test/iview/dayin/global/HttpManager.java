package com.test.iview.dayin.global;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.lzy.okgo.request.PostRequest;
import com.test.iview.dayin.utils.BaseResponse;
import com.test.iview.dayin.utils.Constant;
import com.test.iview.dayin.utils.FromJsonUtils;
import com.test.iview.dayin.utils.SettingUtils;
import com.test.iview.dayin.utils.SharedPreferencesUtils;
import com.test.iview.dayin.utils.ToastUtils;

import java.util.Map;
import okhttp3.Call;
import okhttp3.Response;

//代理模式实现网络请求
public class HttpManager {

    public static void getRequets(String url, final Object tag, Map<String, String> map, final Class clz, final HttpCallBackListener listener) {
        Log.d("OkGoUtil", "method get");
        OkGo.get(url)
                .tag(tag)
//                .headers("Authorization", "本地存储Token")
                .params(map)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        listener.onBefore(request);
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (response.isSuccessful()){
                            Log.d("postRequest",s);
                            if (tag.equals("app")){
                                return;
                            }
                            BaseResponse baseResponse = FromJsonUtils.fromJson(s,clz);

                            if (baseResponse.getCode() != Constant.SUCCESS){
                                Log.e("code:",baseResponse.getCode() + "," + baseResponse.getMessage());
                                if (null != baseResponse.getMessage() && baseResponse.getMessage().length() > 0){
                                    ToastUtils.showShort(baseResponse.getMessage());
                                }
                                return;
                            }

                            listener.onSuccess(baseResponse,s,call,response);
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        if (response == null ){
                            ToastUtils.showToast("网络不可用");
                            return;
                        }
                        Log.e("response.code",response.code() + "");
                        Log.e("response.message",response.message() + "");
                        if (tag.equals("app") && response.code() == 403){
                            System.exit(0);
                        }
                        switch (response.code()){
                            case Constant.TIMEOUT:
                                ToastUtils.showToast("登录超时，请重新登录");
                                SettingUtils.getInstance().setIsLogin(false);
                                SharedPreferencesUtils.setParam("userToekn",null);
                                SharedPreferencesUtils.setParam("uuid",null);
                                SettingUtils.getInstance().loginView();
                                break;
                            case Constant.SERVICEERROR:
                                ToastUtils.showToast("服务器错误");

                            default:
                                if (null != response){
                                    listener.onError(call, response, e);
                                }
                                break;
                        }
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        listener.onAfter(s,e);
                    }
                });
    }


    public static void postRequest(String url, Object tag, Map<String, String> map,final Class clz, final HttpCallBackListener listener) {
        Log.d("OkGoUtil", "method post");
        PostRequest postRequest = OkGo.post(url).tag(tag).params(map);

        if (!(url.equals(IURL.URL_LOGIN) || url.equals(IURL.URL_LOGIN_CODE))){
            postRequest.headers("Authorization", (String)SharedPreferencesUtils.getParam("userToken",""));
        }

        postRequest.execute(new StringCallback() {
            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                listener.onBefore(request);
            }

        @Override
        public void onSuccess(String s, Call call, Response response) {
            if (null != response && response.isSuccessful()){
                Log.d("postRequest",s);
                BaseResponse baseResponse = FromJsonUtils.fromJson(s,clz);

                if (baseResponse.getCode() != Constant.SUCCESS){
                    Log.e("code",baseResponse.getCode() + "," + baseResponse.getMessage());
                    if (null != baseResponse.getMessage() && baseResponse.getMessage().length() > 0){
                        ToastUtils.showShort(baseResponse.getMessage());
                    }
                    return;
                }

                listener.onSuccess(baseResponse,s,call,response);

            }

        }

        @Override
        public void onError(Call call, Response response, Exception e) {
            super.onError(call, response, e);
            switch (response.code()){
                case Constant.TIMEOUT:
                    ToastUtils.showToast("登录超时，请重新登录");
                    SettingUtils.getInstance().setIsLogin(false);
                    SharedPreferencesUtils.setParam("userToekn",null);
                    SharedPreferencesUtils.setParam("uuid",null);
                    SettingUtils.getInstance().loginView();
                    break;
                case Constant.SERVICEERROR:
                    ToastUtils.showToast("服务器错误");

                default:
                    if (null != response){
                        listener.onError(call, response, e);
                    }
                    break;
            }


        }

        @Override
        public void onAfter(String s, Exception e) {
            super.onAfter(s, e);
            listener.onAfter(s,e);
        }
    });
    }


    public static void postJsonRequest(String url, Object tag, String jsonObject, final Class clz, final HttpCallBackListener listener) {
        Log.d("OkGoUtil", "method post");

        OkGo.post(url)
                .tag(tag)
                .headers("Authorization", (String)SharedPreferencesUtils.getParam("userToken",""))
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        listener.onBefore(request);
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (response.isSuccessful()){
                            Log.d("postRequest",s);
                            BaseResponse baseResponse = FromJsonUtils.fromJson(s,clz);

                            if (baseResponse.getCode() != Constant.SUCCESS){
                                Log.e("code:",baseResponse.getCode() + "," + baseResponse.getMessage());
                                if (null != baseResponse.getMessage() && baseResponse.getMessage().length() > 0){
                                    ToastUtils.showShort(baseResponse.getMessage());
                                }
                                return;
                            }

                            listener.onSuccess(baseResponse,s,call,response);
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        switch (response.code()){
                            case Constant.TIMEOUT:
                                ToastUtils.showToast("登录超时，请重新登录");
                                SettingUtils.getInstance().setIsLogin(false);
                                SharedPreferencesUtils.setParam("userToekn",null);
                                SharedPreferencesUtils.setParam("uuid",null);
                                SettingUtils.getInstance().loginView();
                                break;
                            case Constant.SERVICEERROR:
                                ToastUtils.showToast("服务器错误");

                            default:
                                if (null != response){
                                    listener.onError(call, response, e);
                                }
                                break;
                        }
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        listener.onAfter(s,e);
                    }
                });
    }


    public interface HttpCallBackListener{
        void onBefore(BaseRequest request);
        void onSuccess(BaseResponse baseResponse,String s, Call call, Response response);
        void onError(Call call, Response response, Exception e);
        void onAfter(String s, Exception e);
    }


}
