package kr.or.ddit.enumpkg;

public enum MediaType {
	APPLICATION_JSON("application/json;charset=utf-8"), 
	APPLICATION_XML("application/xml;charset=utf-8"), 
	TEXT_HTML("text/html;charset=utf-8"), 
	TEXT_PLAIN("text/plain;charset=utf-8");
	
	public static final String APPLICATION_JSON_VALUE = APPLICATION_JSON.getMimeType();
	public static final String APPLICATION_XML_VALUE = APPLICATION_XML.getMimeType();
	public static final String TEXT_HTML_VALUE = TEXT_HTML.getMimeType();
	public static final String TEXT_PLAIN_VALUE = TEXT_PLAIN.getMimeType();
	
	private MediaType(String mimeType) {
		this.mimeType = mimeType;
	}

	private String mimeType;
	public String getMimeType() {
		return mimeType;
	}
}


/**

public class MediaType {

	public static final String  APPLICATION_JSON = "application/json;charset=utf-8"
	public static final String  APPLICATION_XML = "application/xml;charset=utf-8"
	public static final String  TEXT_HTML = "text/html;charset=utf-8"
	public static final String  TEXT_PLAIN = "text/plain;charset=utf-8"

	private MediaType(String mimeType) {
		this.mimeType = mimeType;
	}


	private String mimeType;

	public String getMimeType() {
		return mimeType;
	}
}

**/