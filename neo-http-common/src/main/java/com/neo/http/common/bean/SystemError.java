package com.neo.http.common.bean;

public enum SystemError implements Error {
    SYS_OK                          ("0", "OK"),
    SYS_BUSY                        ("-1", "内部错误。建议重试，如果多次重试报错请报告技术人员。"),
    SYS_MISSING_PARAMETER           ("sys.MissingParameter", "必填参数没有填，请检查调用时是否填写了此参数，并重试请求。"),
    SYS_INVALID_PARAMETER           ("sys.InvalidParameter","参数值校验不通过。请使用请求参数构造规范化的请求字符串。"),
    SYS_ACCESSKEY_DISABLED          ("sys.AccessKeyDisabled", "AccessKeyId 被禁用。请检查 AccessKey 是否正常可用，请使用状态为启用的 AccessKey。"),
    SYS_INVALID_TIMESTAMP_FORMAT    ("sys.InvalidTimestampFormat", "时间戳格式不对（例如：Date 或 Timestamp）。请检查您的时间戳的格式是否正确。日期格式按照 ISO8601 标准表示并需要使用 UTC 时间，格式为：YYYY-MM-DDThh:mm:ssZ，例如，2014-05-26T12:00:00Z（为北京时间 2013 年 1 月 10 日 20 点 0 分 0 秒）GMT 时间。和服务器时间差在 15 分钟以内算为合法。"),
    SYS_INVALID_TIMESTAMP_EXPIRED   ("sys.InvalidTimestampExpired", "用户时间和服务器时间不在 15 分钟内请检查您的时间戳设置，确认时间戳和服务器时间的差值是否在 15 分钟内。"),
    SYS_INVALID_SIGNATURE_METHOD    ("sys.InvalidSignatureMethod", "签名方法不支持。目前使用的哈希算法是 HMAC-SHA1。"),
    SYS_UNSUPPORTED_HTTP_METHOD     ("sys.UnsupportedHttpMethod", "HTTP 请求方法不支持。建议查看对应接口的 API 调用方式文档。"),
    SYS_INVALID_PARAMETER_FORMAT    ("sys.InvalidParameterFormat", "返回值格式不正确（Format 不支持）。返回格式支持 XML 与 JSON，默认为 JSON。"),
    SYS_INVALID_ACCESSKEYID         ("sys.InvalidAccessKeyId", "AccessKeyId 找不到。请检查调用时是否使用了正确的 AccessKey。"),

    SYS_INVALID_AUTHORIZATION_HEAD  ("sys.InvalidAuthorizationHead", "签名格式不正确"),
    SYS_SIGNATURE_DOSENOT_MATCH     ("sys.SignatureDoesNotMatch", "签名不匹配。请检查 Access Key ID 和 Access Key Secret 是否正确；检查签名方法是否正确。"),
    ;

    private String code;
    private String message;

    SystemError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getRequestId() {
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
