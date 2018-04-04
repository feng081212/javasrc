package com.jstl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.time.TimeUtil;

public class FormatTimestampTag extends TagSupport {

	/**
	 * custom_taglib.tld
	 */
	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	private String format;
	
	@Override
    public int doStartTag() throws JspException {
        try {
        	if(timestamp == 0){
        		timestamp = System.currentTimeMillis() ;
        	}
            super.pageContext.getOut().write(TimeUtil.getInstance().str(timestamp, format));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
}
