package utils;

public class Constants {
	public  static final int iTimeOut = 45; //seconds
	public  static final int iPolling = 1000; //milliseconds	
	public  static final int driverWait = 4000; //Thread sleep		
	public static final String PROPERTIES_NAME = "Myapp.properties"; // TODO Replace 'blaze' with your application name
	public static PropertyReader props = new PropertyReader();	
	
	//*****************To get the URL of the required application************************
	public static String getContextUrl() {
		return props.getString("AppUrl");
	}
	
	//*****************To get the Browser type for the test******************************
	public static String getBrowserName() {
		return props.getString("Browser").toUpperCase();
	}
	
	//*****************To get title of the extent report for the test********************
	public static String getReportTitle() {
		return props.getString("ReportTitle");
	}
	
	//*****************To get the status of HTML reporting*******************************
	public static String getHTMLReportStatus() {
		return props.getString("EnableHTMLReport");
	}
	
	//*****************To get the status of Excel reporting*******************************
		public static String getExcelReportStatus() {
			return props.getString("EnableExcelReport");
	}
		
		public static String huburl() {
			return props.getString("hub");
	}
		
		public static String enableremotedriver() {
			return props.getString("EnableRemoteDriver").toUpperCase();
	}
		
}
