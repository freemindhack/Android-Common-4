package my.android.common.entity;

import java.util.HashMap;
import java.util.Map;

import my.android.common.utils.HttpUtils;


/**
 * <strong>HttpRequest</strong><br/>
 * <ul>
 * <strong>Constructor</strong>
 * <li>{@link my.android.common.entity.HttpRequest#HttpRequest(String)}</li>
 * <li>{@link my.android.common.entity.HttpRequest#HttpRequest(String, java.util.Map)}</li>
 * </ul>
 * <ul>
 * <strong>Setting</strong>
 * <li>{@link #setConnectTimeout(int)}</li>
 * <li>{@link #setReadTimeout(int)}</li>
 * <li>{@link #setParasMap(java.util.Map)}</li>
 * <li>{@link #setUserAgent(String)}</li>
 * <li>{@link #setRequestProperty(String, String)}</li>
 * <li>{@link #setRequestProperties(java.util.Map)}</li>
 * </ul>
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-12
 */
public class HttpRequest {

    private String url;
    private int connectTimeout;
    private int readTimeout;
    private Map<String, String> parasMap;
    private Map<String, String> requestProperties;

    public HttpRequest(String url) {
        this.url = url;
        this.connectTimeout = -1;
        this.readTimeout = -1;
        requestProperties = new HashMap<>();
    }

    public HttpRequest(String url, Map<String, String> parasMap) {
        this.url = url;
        this.parasMap = parasMap;
        this.connectTimeout = -1;
        this.readTimeout = -1;
        requestProperties = new HashMap<>();
    }

    public String getUrl() {
        return url;
    }

    /**
     * @return
     * @see java.net.URLConnection#getConnectTimeout()
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * @param timeoutMillis
     * @see java.net.URLConnection#setConnectTimeout(int)
     */
    public void setConnectTimeout(int timeoutMillis) {
        if (timeoutMillis < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        connectTimeout = timeoutMillis;
    }

    /**
     * @return
     * @see java.net.URLConnection#getReadTimeout()
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * @param timeoutMillis
     * @see java.net.URLConnection#setReadTimeout(int)
     */
    public void setReadTimeout(int timeoutMillis) {
        if (timeoutMillis < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        readTimeout = timeoutMillis;
    }

    /**
     * get paras map
     *
     * @return
     */
    public Map<String, String> getParasMap() {
        return parasMap;
    }

    /**
     * set paras map
     *
     * @param parasMap
     */
    public void setParasMap(Map<String, String> parasMap) {
        this.parasMap = parasMap;
    }

    /**
     * @return paras as string
     */
    public String getParas() {
        return HttpUtils.joinParasWithEncodedValue(parasMap);
    }

    /**
     * @param field
     * @param newValue
     * @see java.net.URLConnection#setRequestProperty(String, String)
     */
    public void setRequestProperty(String field, String newValue) {
        requestProperties.put(field, newValue);
    }

    /**
     * @param field
     * @see java.net.URLConnection#getRequestProperty(String)
     */
    public String getRequestProperty(String field) {
        return requestProperties.get(field);
    }

    /**
     * same to {@link #setRequestProperty(String, String)} filed is User-Agent
     *
     * @param value
     * @see java.net.URLConnection#setRequestProperty(String, String)
     */
    public void setUserAgent(String value) {
        requestProperties.put("User-Agent", value);
    }

    /**
     * @return
     */
    public Map<String, String> getRequestProperties() {
        return requestProperties;
    }

    /**
     * @param requestProperties
     */
    public void setRequestProperties(Map<String, String> requestProperties) {
        this.requestProperties = requestProperties;
    }
}
