package org.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.stereotype.Component;

@Component("DateUtil")
public class DateUtil {
	
	public String nowday() {
        //새글을 올리고 2일동안 앞에 NEW 문구를 띄우기 위함
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal            = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -2); // 2일간 보이도록 하기위해서.
		String nowday           = format.format(cal.getTime());
		return nowday;
	}
}
