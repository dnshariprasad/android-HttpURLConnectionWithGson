package hari.httpurlconnectionwithgson;


public interface TaskListener {
    void before();

    void after(String response);
}
