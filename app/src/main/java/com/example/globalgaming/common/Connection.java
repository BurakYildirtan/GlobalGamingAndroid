package com.example.globalgaming.common;

import android.os.AsyncTask;

import okhttp3.*;
import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//handles the connection to the backend
public class Connection {
    //the internal ip adress and port to reach the backend

//    public static void getResponse(String url, final ResponseCallback callback) {
//        AsyncTask<Void, Void, ResponseResult> asyncTask = new AsyncTask<Void, Void, ResponseResult>() {
//            @Override
//            protected ResponseResult doInBackground(Void... params) {
//                try {
//                    String fullUrl = BASE_URL + url;
//                    JSONArray response = performRequest(fullUrl);
//                    return new ResponseResult(response);
//                } catch (IOException | JSONException e) {
//                    return new ResponseResult(e);
//                }
//            }
//
//            @Override
//            protected void onPostExecute(ResponseResult result) {
//                if (result.isSuccessful()) {
//                    callback.onSuccess(result.getResponse());
//                } else {
//                    callback.onError(result.getException());
//                }
//            }
//        };
//        asyncTask.execute();
//    }


    //performs a request via OkHttpClient
    private static JSONArray performRequest(String url) throws IOException, JSONException {
        OkHttpClient client = getOkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return new JSONArray(responseBody);
            } else {
                throw new IOException("Unexpected response code: " + response.code());
            }
        }
    }

    public static void performPostRequest(String endpoint, final ResponseCallback callback, String... queryParams) {
        AsyncTask<Void, Void, ResponseResult> asyncTask = new AsyncTask<Void, Void, ResponseResult>() {
            @Override
            protected ResponseResult doInBackground(Void... params) {
                try {
                    String fullUrl =  endpoint;
                    JSONArray response = performPostRequest(fullUrl, queryParams);
                    return new ResponseResult(response);
                } catch (IOException | JSONException e) {
                    return new ResponseResult(e);
                }
            }

            @Override
            protected void onPostExecute(ResponseResult result) {
                if (result.isSuccessful()) {
                    callback.onSuccess(result.getResponse());
                } else {
                    callback.onError(result.getException());
                }
            }
        };
        asyncTask.execute();
    }

    private static JSONArray performPostRequest(String url, String... queryParams) throws IOException, JSONException {
        OkHttpClient client = getOkHttpClient();

        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        // FÃ¼ge die Query-Parameter hinzu
        for (int i = 0; i < queryParams.length; i += 2) {
            formBodyBuilder.add(queryParams[i], queryParams[i + 1]);
        }

        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return new JSONArray(responseBody);
            } else {
                throw new IOException("Unexpected response code: " + response.code());
            }
        }
    }

    public static JSONArray performRequestBaseUrl(String url) throws IOException, JSONException {
        String fullUrl = Constants.BASE_URL + url;
        OkHttpClient client = getOkHttpClient();

        Request request = new Request.Builder()
                .url(fullUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return new JSONArray(responseBody);
            } else {
                throw new IOException("Unexpected response code: " + response.code());
            }
        }
    }

    //builds a new OkHttpClient with required certificates and ssl socket
    public static OkHttpClient getOkHttpClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private static class ResponseResult {
        private JSONArray response;
        private Exception exception;

        ResponseResult(JSONArray response) {
            this.response = response;
        }

        ResponseResult(Exception exception) {
            this.exception = exception;
        }

        boolean isSuccessful() {
            return exception == null;
        }

        JSONArray getResponse() {
            return response;
        }

        Exception getException() {
            return exception;
        }
    }

    public interface ResponseCallback {
        void onSuccess(JSONArray response);

        void onError(Exception e);
    }

    public static void postRequest(String url, String jsonBody, final ResponseCallback callback) {
        AsyncTask<Void, Void, ResponseResult> asyncTask = new AsyncTask<Void, Void, ResponseResult>() {
            @Override
            protected ResponseResult doInBackground(Void... params) {
                try {
                    String fullUrl = url;
                    JSONArray response = performPostRequest(fullUrl, jsonBody);
                    return new ResponseResult(response);
                } catch (IOException | JSONException e) {
                    return new ResponseResult(e);
                }
            }

            @Override
            protected void onPostExecute(ResponseResult result) {
                if (result.isSuccessful()) {
                    callback.onSuccess(result.getResponse());
                } else {
                    callback.onError(result.getException());
                }
            }
        };
        asyncTask.execute();
    }

    // neue Methode für POST-Anfrage
    private static JSONArray performPostRequest(String url, String jsonBody) throws IOException, JSONException {
        OkHttpClient client = getOkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return new JSONArray(responseBody);
            } else {
                throw new IOException("Unexpected response code: " + response.code());
            }
        }
    }
}