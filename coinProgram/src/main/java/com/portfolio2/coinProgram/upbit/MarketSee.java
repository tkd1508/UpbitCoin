package com.portfolio2.coinProgram.upbit;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import com.portfolio2.coinProgram.CoinProgramApplication;
import com.portfolio2.coinProgram.common.util.UISMap;

import ch.qos.logback.classic.Logger;

public class MarketSee {
	
	private Logger logger = (Logger) LoggerFactory.getLogger(MarketSee.class);
	
	public List<UISMap> marketSee() throws ClientProtocolException, IOException, NoSuchAlgorithmException, InterruptedException {
		
		int cnt = 0;
		List<UISMap> list = new ArrayList<UISMap>();
		
		//market 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet("https://api.upbit.com/v1/market/all?isDetails=true");
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        //SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'09:");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:00");
        //SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy-MM-dd'T'09:00");
        
        String dateNow = formatter.format(cal.getTime());
        //String formatedNow = formatter2.format(cal.getTime());
        String dateCheck = formatter3.format(cal.getTime());
        //String dateMinCheck = formatter4.format(cal.getTime());
        
        int checkMin = dateCheck.indexOf(dateNow);
        //int LarrySwich = dateMinCheck.indexOf(dateNow);
        
        /*
        if(dateNow.substring(0,14).equals(formatedNow) && CoinProgramApplication.LarryWilliams) {
 			CoinProgramApplication.LarryWilliams = true;
		}else {
			CoinProgramApplication.LarryWilliams = false;
		}
		*/
        
        if(checkMin != -1) {
        	Account upbitWallet = new Account();
        	upbitWallet.myMoney(null, "mode3");
        }
		
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           String market = "";
//	           String koreanName = "";
//	           String englishName = "";
//	           String marketWarning = "";

	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object

	           for(int i=0; i<result.length(); i++){
	        	   UISMap input = new UISMap();
	        	   JSONObject aa = result.getJSONObject(i);
	        	   if(aa.getString("market").indexOf("KRW") != -1 && aa.getString("market_warning").equals("NONE")) {
	        		   if(!aa.getString("market").equals("KRW-ETC") && !aa.getString("market").equals("KRW-EGLD") && !aa.getString("market").equals("KRW-SUI") && !aa.getString("market").equals("KRW-SOL")) {
	        			   market = aa.getString("market");
//		        		   koreanName = aa.getString("korean_name");
//		        		   englishName = aa.getString("english_name");
//		        		   marketWarning = aa.getString("market_warning");
//			        	   System.out.println(market + ", " + koreanName + ", " + englishName + "( "+ marketWarning +" )");
			        	   
	        			   /*
			        	   if(dateNow.substring(0,14).equals(formatedNow) && CoinProgramApplication.LarryWilliams){
			        		// 해당 종목 일봉 캔들바 확인 + 매수 종목 확인
				        	   input = dayCandleInfo(market, 10);
			        	   }else {
			        		// 해당 종목 분봉 캔들바 모니터링 + 매수 종목 확인
				        	   input = minCandleInfo(market, 1);
			        	   }
			        	   */
	        			   
	        			   // 해당 종목 분봉 캔들바 모니터링 + 매수 종목 확인
			        	   input = minCandleInfo(market, 1);
			        	   
			        	   if(input.size() > 0) {
			        		   logger.info("/n"+input);
			        		   list.add(input);
			        	   }
			        	   cnt++;
	        		   }
	        	   }
	        	}
	            /*
	            if(LarrySwich != -1 && !CoinProgramApplication.LarryWilliams) { // 다음날 9시 00분이 되면 래리 윌리엄스 전략 진행.
	    			CoinProgramApplication.LarryWilliams = true;
		   		}
	            */
	        }
			logger.info("INFO 한국 마켓 : ["+ cnt +"]개 조회");
			//logger.info("INFO 한국 마켓 : ["+ cnt +"]개 ======== 변동성 돌파 전략 : ["+CoinProgramApplication.LarryWilliams +"]");
		}catch(Exception e) {
			
		}
		return list;
	}
	
	public UISMap minCandleInfo(String market, int count) throws ClientProtocolException, IOException {
		
		String URL = "https://api.upbit.com/v1/candles/minutes/1?market="+market+"&count="+count;
		
		// 분봉 캔들 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(URL);
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'09:");
        
        String dateNow = formatter.format(cal.getTime());
        String dateCheck = formatter2.format(cal.getTime());
        
        int checkHour = dateNow.indexOf(dateCheck);
		
		UISMap input = new UISMap();
		
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           Double opening_price = 0.0;
	           Double trade_price = 0.0;
	           Double high_price = 0.0;
	           /*
	           String candle_date_time_kst = "";
	           Integer unit = 0;
	           String candle_date_time_utc = "";
	           Double candle_acc_trade_volume = 0.0;
	           Double low_price = 0.0;
	           Double candle_acc_trade_price = 0.0;
	           Long timestamp;
	           */

	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
	           
	           JSONObject aa = result.getJSONObject(0);
	           opening_price = dayOpeningPrice(market, count); // 일봉 시가
	           high_price = dayHighPrice(market, count); // 일봉 고가
	           trade_price = aa.getDouble("trade_price"); //현재가

	           if(checkHour != -1) { // 9시 ~ 9시 59분
	        	   if(trade_price > opening_price*1.015 && trade_price < opening_price*1.025 && high_price <= trade_price*1.005) {
		        	   if(orderBook(market, "mode2") && dayCandledegree(market , 5)){
		        		   input.set("market", market);
		        		   //CoinProgramApplication.LarryWilliams = false;
		        	   }
		           } else if(trade_price > opening_price*1.06 && trade_price < opening_price*1.07 && high_price <= trade_price*1.005) {
		        	   if(orderBook(market, "mode2") && dayCandledegree(market , 5)){
		        		   input.set("market", market);
		        		   //CoinProgramApplication.LarryWilliams = false;
		        	   }
		           }
	           }else {
	        	   if(trade_price > opening_price*1.025 && trade_price < opening_price*1.035 && high_price <= trade_price*1.005) {
		        	   if(orderBook(market, "mode2") && dayCandledegree(market , 5)){
		        		   input.set("market", market);
		        		   //CoinProgramApplication.LarryWilliams = false;
		        	   }
		           } else if(trade_price > opening_price*1.06 && trade_price < opening_price*1.07 && high_price <= trade_price*1.005) {
		        	   if(orderBook(market, "mode2") && dayCandledegree(market , 5)){
		        		   input.set("market", market);
		        		   //CoinProgramApplication.LarryWilliams = false;
		        	   }
		           }
	           }
	           
	           /*
	           for(int i=0; i<result.length(); i++){
        		   candle_date_time_kst = aa.getString("candle_date_time_kst"); //캔들 기준 시각(KST 기준)
        		   unit = aa.getInt("unit");
        		   high_price = aa.getDouble("high_price");
        		   low_price = aa.getDouble("low_price");
        		   candle_date_time_utc = aa.getString("candle_date_time_utc"); //캔들 기준 시각(UTC 기준)
        		   candle_acc_trade_volume = aa.getDouble("candle_acc_trade_volume"); // 누적 거래량
        		   candle_acc_trade_price = aa.getDouble("candle_acc_trade_price"); // 누적 거래 금액
        		   timestamp = aa.getLong("timestamp"); // 해당 캔들에서 마지막 틱이 저장된 시각
        		   input.set("market", market);
           		   input.set("trade_price", trade_price);
	        	}
	        	*/
	        }
		}catch(Exception e) {
			
		}	
		return input;
	}
	
	public UISMap dayCandleInfo(String market, int count) throws ClientProtocolException, IOException {
		
		String URL = "https://api.upbit.com/v1/candles/days?market="+market+"&count="+count;
		
		// 일봉 캔들 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(URL);
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		UISMap input = new UISMap();
		
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           Double c_opening_price = 0.0;
	           Double c_trade_price = 0.0;
	           Double b_high_price = 0.0;
	           Double b_low_price = 0.0;
	           Double yesterday_range = 0.0;
	           Double target = 0.0;
	           
//	           String candle_date_time_kst = ""; //yyyy-MM-dd'T'HH:mm:ss
//	           Double candle_acc_trade_volume = 0.0;
//	           Double candle_acc_trade_price = 0.0;
//	           Long timestamp = 0L;
//	           Double prev_closing_price = 0.0;
//	           Double change_price = 0.0;
//	           Double change_rate = 0.0;
//	           Double converted_trade_price = 0.0;
//	           String candle_date_time_utc = ""; //yyyy-MM-dd'T'HH:mm:ss 
	           
	           Calendar cal = Calendar.getInstance();
	           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:00");
	           String formatedNow = formatter.format(cal.getTime());

	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
	           
	           Double AVG_countDaycandlePrice = AVG_candlePrice(result, count); //count 개수의 평선가
	           
	           JSONObject aa = result.getJSONObject(0);
	           JSONObject bb = result.getJSONObject(1);
	           // 10일평균선보다 현재가가 위로 올라가있는 종목들만 선정
	           if(AVG_countDaycandlePrice < aa.getDouble("opening_price") && dayCandledegree(market, 20)) {
	        	   // 09시 새로운 캔틀봉이 생성되었을 경우만 선정
	        	   if(aa.getString("candle_date_time_kst").indexOf(formatedNow) != -1) {
	        		   c_opening_price = aa.getDouble("opening_price"); //당일 9시 00분 시가
	        		   c_trade_price = aa.getDouble("trade_price"); //당일 현재가
	        		   b_high_price = bb.getDouble("high_price"); //전일 고가
	        		   b_low_price = bb.getDouble("low_price"); //전일 저가
		               if(c_opening_price > b_high_price*0.8) {
		            	   yesterday_range = b_high_price - b_low_price;
			               target = c_opening_price + yesterday_range * 0.5;
			               if(c_trade_price >= target) {
			            		input.set("market", market);
			            		input.set("target", target);
			               }
		               }
	        	   }
	           }
	        }
		}catch(Exception e) {
			
		}
		return input;
	}

	public void weekCandleInfo() throws ClientProtocolException, IOException {
		
		// 주봉 캔들 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet("https://api.upbit.com/v1/candles/weeks?market=KRW-BTC&count=200"); // 옵션 여기다가 주는거임
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           String market = "";
	           Double opening_price = 0.0;
	           String candle_date_time_kst = "";
	           Double trade_price = 0.0;
	           Double high_price = 0.0;
	           String candle_date_time_utc = "";
	           Double candle_acc_trade_volume = 0.0;
	           Double low_price = 0.0;
	           Double candle_acc_trade_price = 0.0;
	           Long timestamp;
	           String first_day_of_period = "";

	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object

	           for(int i=0; i<result.length(); i++){
	        	   JSONObject aa = result.getJSONObject(i);
	        	   if(aa.get("market").toString().indexOf("KRW") != -1) {
	        		   market = aa.getString("market");
	        		   opening_price = aa.getDouble("opening_price");
	        		   candle_date_time_kst = aa.getString("candle_date_time_kst");
	        		   trade_price = aa.getDouble("trade_price");
	        		   high_price = aa.getDouble("high_price");
	        		   candle_date_time_utc = aa.getString("candle_date_time_utc");
	        		   candle_acc_trade_volume = aa.getDouble("candle_acc_trade_volume");
	        		   low_price = aa.getDouble("low_price");
	        		   candle_acc_trade_price = aa.getDouble("candle_acc_trade_price");
	        		   timestamp = aa.getLong("timestamp");
	        		   first_day_of_period = aa.getString("first_day_of_period");
//		        	   System.out.println(market + ", " + opening_price + ", " + candle_date_time_kst + ", " + trade_price + ", " + high_price + ", " + candle_date_time_utc + ", " + candle_acc_trade_volume + ", " + low_price + ", " + candle_acc_trade_price + ", " + timestamp + ", " + first_day_of_period );
	        	   }
	        	}
	        }
		}catch(Exception e) {
			
		}	
	}
	
	public void currentCoinInfo() throws ClientProtocolException, IOException {
		
		// 현재가 정보 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet("https://api.upbit.com/v1/ticker?markets=KRW-BTC,KRW-ETH"); // 옵션 여기다가 주는거임
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           String market = "";
	           String trade_date = "";
	           String trade_time = "";
	           String trade_date_kst = "";
	           String trade_time_kst = "";
	           Double opening_price = 0.0;
	           Double high_price = 0.0;
	           Double low_price = 0.0;
	           Double trade_price = 0.0;
	           Double prev_closing_price = 0.0;
	           String change = "";
	           Double change_price = 0.0;
	           Double change_rate = 0.0;
	           Double signed_change_price = 0.0;
	           Double signed_change_rate = 0.0;
	           Double trade_volume = 0.0;
	           Double acc_trade_price = 0.0;
	           Double acc_trade_price_24h = 0.0;
	           Double acc_trade_volume = 0.0;
	           Double acc_trade_volume_24h = 0.0;
	           Double highest_52_week_price = 0.0;
	           String highest_52_week_date = "";
	           Double lowest_52_week_price = 0.0;
	           String lowest_52_week_date = "";
	           Long timestamp;
	           Long trade_timestamp;

	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object

	           for(int i=0; i<result.length(); i++){
	        	   JSONObject aa = result.getJSONObject(i);
	        	   if(aa.get("market").toString().indexOf("KRW") != -1) {
	        		   market = aa.getString("market");
	        		   trade_date = aa.getString("trade_date");
	        		   trade_time = aa.getString("trade_time");
	        		   trade_date_kst = aa.getString("trade_date_kst");
	        		   trade_time_kst = aa.getString("trade_time_kst");
	        		   opening_price = aa.getDouble("opening_price");
	        		   trade_price = aa.getDouble("trade_price");
	        		   high_price = aa.getDouble("high_price");
	        		   low_price = aa.getDouble("low_price");
	        		   prev_closing_price = aa.getDouble("prev_closing_price");
	        		   change = aa.getString("change");
	        		   change_price = aa.getDouble("change_price");
	        		   change_rate = aa.getDouble("change_rate");
	        		   signed_change_price = aa.getDouble("signed_change_price");
	        		   signed_change_rate = aa.getDouble("signed_change_rate");
	        		   trade_volume = aa.getDouble("trade_volume");
	        		   acc_trade_price_24h = aa.getDouble("acc_trade_price_24h");
	        		   acc_trade_volume = aa.getDouble("acc_trade_volume");
	        		   acc_trade_volume_24h = aa.getDouble("acc_trade_volume_24h");
	        		   highest_52_week_price = aa.getDouble("highest_52_week_price");
	        		   highest_52_week_date = aa.getString("highest_52_week_date");
	        		   lowest_52_week_price = aa.getDouble("lowest_52_week_price");
	        		   lowest_52_week_date = aa.getString("lowest_52_week_date");
	        		   timestamp = aa.getLong("timestamp");
	        		   trade_timestamp = aa.getLong("trade_timestamp");
		        	    
//		        	    System.out.println(market + ", " + trade_date + ", " + trade_time + ", " + trade_date_kst + ", " + trade_time_kst + ", " 
//		        	    + opening_price + ", " + trade_price + ", " + high_price + ", " + low_price + ", " + ", " + prev_closing_price + ", " 
//		        	    + change + ", " + change_price + ", " + change_rate + ", " + signed_change_price + ", " + signed_change_rate
//		        	    + trade_volume + ", " + acc_trade_price_24h + ", " + acc_trade_volume + ", " + acc_trade_volume_24h + ", " + ", " + highest_52_week_price + ", " 
//		        	    + highest_52_week_date + ", " + lowest_52_week_price + ", " + lowest_52_week_date + ", " + timestamp + ", " + ", " + trade_timestamp );
	        	   }
	        	}
	        }
		}catch(Exception e) {
			
		}
	}
	
	public boolean orderBook(String market, String mode) throws ClientProtocolException, IOException {
		
		// 호가 정보 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet("https://api.upbit.com/v1/orderbook?markets="+market); // 옵션 여기다가 주는거임
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		boolean orderCheck = false;
		
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           Double total_ask_size = 0.0; // 호가 매도 총 잔량
	           Double total_bid_size = 0.0; // 호가 매수 총 잔량
	           /*
	           List orderbook_units = null;
	           Double ask_price = 0.0;
	           Double bid_price = 0.0;
	           Double ask_size = 0.0;
	           Double bid_size = 0.0;
	           Long timestamp;
	           */

	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
	           
	           JSONObject aa = result.getJSONObject(0);
	           total_ask_size = aa.getDouble("total_ask_size");
    		   total_bid_size = aa.getDouble("total_bid_size");
    		   
    		   if(mode == "mode1") {
    			   if(total_ask_size/total_bid_size > 2.0) {
        			   orderCheck = true;
        		   }   
    		   }else if(mode == "mode2") {
    			   if(total_bid_size/total_ask_size > 2.0) {
        			   orderCheck = true;
        		   }   
    		   }
    		   
	           
    		   /*
	           for(int i=0; i<result.length(); i++){
	        	   JSONObject aa = result.getJSONObject(i);
	        	   total_ask_size = aa.getDouble("total_ask_size");
        		   total_bid_size = aa.getDouble("total_bid_size");
        		   Object tt = aa.get("orderbook_units");
        		   ask_price = aa.getDouble("ask_price");
        		   bid_price = aa.getDouble("bid_price");
        		   ask_size = aa.getDouble("ask_size");
        		   bid_size = aa.getDouble("bid_size");
        		   timestamp = aa.getLong("timestamp");
	        	}
	        	*/
	        }
		}catch(Exception e) {
			
		}
		return orderCheck;
	}
	
	public Double AVG_candlePrice(JSONArray result, int Num) {
		
		Double sum_trade_price = 0.0;
		
		for(int i=0; i<result.length(); i++){
     	  JSONObject aa = result.getJSONObject(i);
     	  sum_trade_price += aa.getDouble("trade_price");
        }
		
		return sum_trade_price/Num;
	}
	
	public Double dayOpeningPrice(String market, int count) throws ClientProtocolException, IOException {
		
		String URL = "https://api.upbit.com/v1/candles/days?market="+market+"&count="+count;
		
		// 일봉 캔들 시가 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(URL);
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		Double dayOpeningPrice = 0.0;
		 
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
	           JSONObject aa = result.getJSONObject(0);
	           dayOpeningPrice = aa.getDouble("opening_price");
	        }
		}catch(Exception e) {
			
		}
		return dayOpeningPrice;
	}
	
	public Double dayHighPrice(String market, int count) throws ClientProtocolException, IOException {
		
		String URL = "https://api.upbit.com/v1/candles/days?market="+market+"&count="+count;
		
		// 일봉 캔들 시가 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(URL);
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		Double dayHighPrice = 0.0;
		 
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
	           JSONObject aa = result.getJSONObject(0);
	           dayHighPrice = aa.getDouble("high_price");
	        }
		}catch(Exception e) {
			
		}
		return dayHighPrice;
	}
	
	public double minTradePrice(String market, int count) throws ClientProtocolException, IOException {
		
		String URL = "https://api.upbit.com/v1/candles/minutes/1?market="+market+"&count="+count;
		
		// 분봉 현재 가격 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(URL);
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		Double trade_price = 0.0;
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 

	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
	           
	           JSONObject aa = result.getJSONObject(0);
	           trade_price = aa.getDouble("trade_price"); //현재가
	        }
		}catch(Exception e) {
			
		}	
		return trade_price;
	}
	
	public boolean dayCandledegree(String market, int count) throws ClientProtocolException, IOException {
		
		String URL = "https://api.upbit.com/v1/candles/days?market="+market+"&count="+count;
		
		// 일봉 캔들 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(URL);
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		boolean updegree = false;
		
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           Double aa_trade_price = 0.0;
	           Double bb_trade_price = 0.0;
	           Double cc_trade_price = 0.0;
	           Double degree10 = 0.0;
	           Double degree20 = 0.0;
	           
	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
	           
	           /* 장기
	           JSONObject aa = result.getJSONObject(0);
	           JSONObject bb = result.getJSONObject(9);
	           JSONObject cc = result.getJSONObject(19);
	           
	           aa_trade_price = aa.getDouble("trade_price"); // 현재일
	           bb_trade_price = bb.getDouble("trade_price"); // 10일전
	           cc_trade_price = cc.getDouble("trade_price"); // 20일전
	           
	           if(aa_trade_price > bb_trade_price && aa_trade_price > cc_trade_price && bb_trade_price > cc_trade_price) {
	        	   degree10 = 90 - Math.toDegrees(Math.atan2(10, aa_trade_price/bb_trade_price));
		           degree20 = 90 - Math.toDegrees(Math.atan2(20, aa_trade_price/cc_trade_price));
		           
		           logger.info("20일 각도 : ["+ degree20 +"] 10일 각도 : ["+ degree10 +"] 마켓 명 : ["+ market +"]");
		           
		           if(degree20 > 2.8) { 
		        	   if(degree20*2 < degree10) { 
		        		   updegree = true;
		        		   logger.info("상향 각도 만족 : ["+ market +"]");
		        	   }
		           }
	           }
	           */
	           

	           JSONObject aa = result.getJSONObject(1);
	           JSONObject bb = result.getJSONObject(2);
	           JSONObject cc = result.getJSONObject(3);
	           
	           aa_trade_price = aa.getDouble("trade_price"); // 1일전
	           bb_trade_price = bb.getDouble("trade_price"); // 2일전
	           cc_trade_price = cc.getDouble("trade_price"); // 3일전
	           
	           if(aa_trade_price > bb_trade_price && aa_trade_price > cc_trade_price && bb_trade_price > cc_trade_price) {
	        	   degree10 = Math.toDegrees(Math.atan2(1, aa_trade_price/bb_trade_price));
		           degree20 = Math.toDegrees(Math.atan2(2, aa_trade_price/cc_trade_price));
		           
		           logger.info("3일 각도 : ["+ degree20 +"] > 2일 각도 : ["+ degree10 +"] 마켓 명 : ["+ market +"]");
		           /*
		           if(degree20 > 2.8) { 
		        	   if(degree20*2 < degree10) { 
		        		   updegree = true;
		        		   logger.info("상향 각도 만족 : ["+ market +"]");
		        	   }
		           }
		           */
		           updegree = true;
        		   logger.info("상향 각도 만족 : ["+ market +"]");
	           }
	           
	        }
		}catch(Exception e) {
			
		}
		return updegree;
	}
	
	/*
	 * MACD 보조지표
	 * */
	public List<UISMap> marketsee2() throws ClientProtocolException, IOException, NoSuchAlgorithmException, InterruptedException {
		
		int cnt = 0;
		List<UISMap> list = new ArrayList<UISMap>();
		
		//market 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet("https://api.upbit.com/v1/market/all?isDetails=true");
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		/*
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        //SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'09:");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:00");
        //SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy-MM-dd'T'09:00");
        
        String dateNow = formatter.format(cal.getTime());
        //String formatedNow = formatter2.format(cal.getTime());
        String dateCheck = formatter3.format(cal.getTime());
        //String dateMinCheck = formatter4.format(cal.getTime());
        
        int checkMin = dateCheck.indexOf(dateNow);
        //int LarrySwich = dateMinCheck.indexOf(dateNow);
        
        
        if(dateNow.substring(0,14).equals(formatedNow) && CoinProgramApplication.LarryWilliams) {
 			CoinProgramApplication.LarryWilliams = true;
		}else {
			CoinProgramApplication.LarryWilliams = false;
		}
		
        
        if(checkMin != -1) {
        	Account upbitWallet = new Account();
        	upbitWallet.myMoney(null, "mode3"); 내 돈 체크
        }
		*/
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           String market = "";
//	           String koreanName = "";
//	           String englishName = "";
//	           String marketWarning = "";

	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object

	           for(int i=0; i<result.length(); i++){
	        	   UISMap input = new UISMap();
	        	   JSONObject aa = result.getJSONObject(i);
	        	   if(aa.getString("market").indexOf("KRW") != -1 && aa.getString("market_warning").equals("NONE")) {
	        		   if(!aa.getString("market").equals("KRW-ETC") && !aa.getString("market").equals("KRW-EGLD") && !aa.getString("market").equals("KRW-SUI") && !aa.getString("market").equals("KRW-SOL")) {
	        			   market = aa.getString("market");
//		        		   koreanName = aa.getString("korean_name");
//		        		   englishName = aa.getString("english_name");
//		        		   marketWarning = aa.getString("market_warning");
//			        	   System.out.println(market + ", " + koreanName + ", " + englishName + "( "+ marketWarning +" )");
			        	   
	        			   /*
			        	   if(dateNow.substring(0,14).equals(formatedNow) && CoinProgramApplication.LarryWilliams){
			        		// 해당 종목 일봉 캔들바 확인 + 매수 종목 확인
				        	   input = dayCandleInfo(market, 10);
			        	   }else {
			        		// 해당 종목 분봉 캔들바 모니터링 + 매수 종목 확인
				        	   input = minCandleInfo(market, 1);
			        	   }
			        	   */
	        			   
	        			   // 해당 종목 분봉 캔들바 모니터링 + 매수 종목 확인
			        	   //input = minCandleInfo(market, 1);
			        	   
	        			   //MACD
	        			   input = marketMACD(market, 200); 
			        	   
			        	   if(input.size() > 0) {
			        		   logger.info("/n"+input);
			        		   list.add(input);
			        	   }
			        	   cnt++;
	        		   }
	        	   }
	        	}
	        }
			logger.info("INFO 한국 마켓 : ["+ cnt +"]개 조회");
		}catch(Exception e) {
			
		}
		return list;
	}

	public UISMap marketMACD(String market, int count) throws ClientProtocolException, IOException {
		
		UISMap input = new UISMap();
		/*
		String URL = "https://api.upbit.com/v1/candles/minutes/1?market="+market+"&count="+count;
		
		// 분봉 캔들 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(URL);
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'09:");
        
        String dateNow = formatter.format(cal.getTime());
        String dateCheck = formatter2.format(cal.getTime());
        
        int checkHour = dateNow.indexOf(dateCheck);
		
		UISMap input = new UISMap();
		
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           Double opening_price = 0.0;
	           Double trade_price = 0.0;
	           Double high_price = 0.0;
	           /*
	           String candle_date_time_kst = "";
	           Integer unit = 0;
	           String candle_date_time_utc = "";
	           Double candle_acc_trade_volume = 0.0;
	           Double low_price = 0.0;
	           Double candle_acc_trade_price = 0.0;
	           Long timestamp;
	           

	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
	           
	           JSONObject aa = result.getJSONObject(0);
	           opening_price = dayOpeningPrice(market, count); // 일봉 시가
	           high_price = dayHighPrice(market, count); // 일봉 고가
	           trade_price = aa.getDouble("trade_price"); //현재가

	           if(checkHour != -1) { // 9시 ~ 9시 59분
	        	   if(trade_price > opening_price*1.015 && trade_price < opening_price*1.025 && high_price <= trade_price*1.005) {
		        	   if(orderBook(market, "mode2") && dayCandledegree(market , 5)){
		        		   input.set("market", market);
		        		   //CoinProgramApplication.LarryWilliams = false;
		        	   }
		           } else if(trade_price > opening_price*1.06 && trade_price < opening_price*1.07 && high_price <= trade_price*1.005) {
		        	   if(orderBook(market, "mode2") && dayCandledegree(market , 5)){
		        		   input.set("market", market);
		        		   //CoinProgramApplication.LarryWilliams = false;
		        	   }
		           }
	           }else {
	        	   if(trade_price > opening_price*1.025 && trade_price < opening_price*1.035 && high_price <= trade_price*1.005) {
		        	   if(orderBook(market, "mode2") && dayCandledegree(market , 5)){
		        		   input.set("market", market);
		        		   //CoinProgramApplication.LarryWilliams = false;
		        	   }
		           } else if(trade_price > opening_price*1.06 && trade_price < opening_price*1.07 && high_price <= trade_price*1.005) {
		        	   if(orderBook(market, "mode2") && dayCandledegree(market , 5)){
		        		   input.set("market", market);
		        		   //CoinProgramApplication.LarryWilliams = false;
		        	   }
		           }
	           }
	           
	           /*
	           for(int i=0; i<result.length(); i++){
        		   candle_date_time_kst = aa.getString("candle_date_time_kst"); //캔들 기준 시각(KST 기준)
        		   unit = aa.getInt("unit");
        		   high_price = aa.getDouble("high_price");
        		   low_price = aa.getDouble("low_price");
        		   candle_date_time_utc = aa.getString("candle_date_time_utc"); //캔들 기준 시각(UTC 기준)
        		   candle_acc_trade_volume = aa.getDouble("candle_acc_trade_volume"); // 누적 거래량
        		   candle_acc_trade_price = aa.getDouble("candle_acc_trade_price"); // 누적 거래 금액
        		   timestamp = aa.getLong("timestamp"); // 해당 캔들에서 마지막 틱이 저장된 시각
        		   input.set("market", market);
           		   input.set("trade_price", trade_price);
	        	}
	        	
	        }
		}catch(Exception e) {
			
		}	*/
		
		
		String URL = "https://api.upbit.com/v1/candles/days?market="+market+"&count="+count;
		
		// 일봉 캔들 시가 조회
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet(URL);
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		
		Double dayOpeningPrice = 0.0;
		 
		try {
			if (entity != null) {
	           String retSrc = EntityUtils.toString(entity); 
	           // parsing JSON
	           JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
	           JSONObject aa = result.getJSONObject(0);
	           dayOpeningPrice = aa.getDouble("opening_price");
	        }
		}catch(Exception e) {
			
		}
		
		return input;
	}
	

}
