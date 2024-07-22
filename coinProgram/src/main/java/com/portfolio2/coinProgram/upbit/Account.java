package com.portfolio2.coinProgram.upbit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.portfolio2.coinProgram.CoinProgramApplication;
import com.portfolio2.coinProgram.common.util.UISMap;

import ch.qos.logback.classic.Logger;

public class Account {
	
	private Logger logger = (Logger) LoggerFactory.getLogger(Account.class);

	String accessKey = "";
    String secretKey = "";
    String serverUrl = "https://api.upbit.com";
    
    public void myMoney(List<UISMap> market, String mode) throws NoSuchAlgorithmException, InterruptedException {
    	Path path = Paths.get("C:\\upbit\\upbit.txt");
        
        try{
    		List<String> allLines = Files.readAllLines(path);
    		accessKey = allLines.get(0);
    		secretKey = allLines.get(1);

            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String jwtToken = JWT.create()
                    .withClaim("access_key", accessKey)
                    .withClaim("nonce", UUID.randomUUID().toString())
                    .sign(algorithm);

            String authenticationToken = "Bearer " + jwtToken;

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
               String retSrc = EntityUtils.toString(entity); 
               String currency = "";
               double balance = 0.0;
               //String locked = "";
               double avg_buy_price = 0.0;
               //boolean avg_buy_price_modified = false;
               String unit_currency = "";
               double min_buy_money = 0.0;
               String market_name = "";
               
               Calendar cal = Calendar.getInstance();
	           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	           SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'08:59");
	           String dateNow = formatter.format(cal.getTime());
	           String formatedNow = formatter2.format(cal.getTime());
	           
               // parsing JSON
               JSONArray result = new JSONArray(retSrc); //Convert String to JSON Object
               
               if(mode.equals("mode1")) { // 예수금 조회 + 래리 윌리엄스 방식으로 구매.
            	   JSONObject aa = result.getJSONObject(0);
        		   currency = aa.getString("currency"); //화폐를 의미하는 영문 대문자 코드
        		   balance = Double.parseDouble(aa.getString("balance")) / 0.8; // 80% 매수
        		   min_buy_money = balance/(market.size()+1);
        		   if(min_buy_money > 5000) { //최소 주문 금액
        			   for(int i=0; i<market.size(); i++) {
        				   order(market.get(i).getString("market"), market.get(i).getDouble("target"), min_buy_money, "buy", "price"); // 시장가 매수
        				   Thread.sleep(500);
        				   CoinProgramApplication.LarryWilliams = false;
        			   }
        			   Thread.sleep(1000*60);
        		   }else {
        			   CoinProgramApplication.operate = false; // 매수 종목 코인 리스트 조회 동작 그만
        		   }
               } else if(mode.equals("mode4")) { // 예수금 조회 + 분봉 초단다 방식으로 구매.
            	   JSONObject aa = result.getJSONObject(0);
        		   currency = aa.getString("currency"); //화폐를 의미하는 영문 대문자 코드
        		   balance = Double.parseDouble(aa.getString("balance")); // 풀매수
        		   min_buy_money = balance/market.size();
        		   if(min_buy_money > 5000) { //최소 주문 금액
        			   for(int i=0; i<market.size(); i++) {
        				   order(market.get(i).getString("market"), 0.0, min_buy_money*0.999, "buy", "price"); // 시장가 매수
        				   Thread.sleep(500);
        			   }
        		   }else {
        			   CoinProgramApplication.operate = false; // 매수 종목 코인 리스트 조회 동작 그만
        		   }
               } else if (mode.equals("mode2")) { // 매도 물량 모니터링
            	   for(int i=0; i<result.length(); i++){
                	   JSONObject aa = result.getJSONObject(i);
            		   currency = aa.getString("currency"); //화폐를 의미하는 영문 대문자 코드
            		   unit_currency = aa.getString("unit_currency"); //평단가 기준 화폐
            		   avg_buy_price = Double.parseDouble(aa.getString("avg_buy_price")); //평단가
            		   balance = Double.parseDouble(aa.getString("balance")); // 보유 수량
            		   if(avg_buy_price * balance > 5000) {
            			   if(!currency.equals("KRW") && !currency.equals("ETC") && !currency.equals("USDT") && !currency.equals("VTHO") && !currency.equals("APENFT")
                  				 && !currency.equals("SOLO") && !currency.equals("ETHW") && !currency.equals("ETHF")) {
                  			  
                  			   MarketSee marketSee = new MarketSee();
                  			   market_name = unit_currency+"-"+currency;
                  			   
                  			   double trade_price = 0.0;
                  			   double high_price = 0.0;
                  			   trade_price = marketSee.minTradePrice(market_name, 2); // 현재가
                  			   high_price = marketSee.dayHighPrice(market_name, 2); // 일봉 고가
                  			   
                  			   if(avg_buy_price*1.013 < trade_price && trade_price < high_price*0.988) { // 1825 < 1830
                				   order(market_name, 0.0, balance, "sell", "market"); // 시장가 매도
                				   logger.info("최고가 -1.3% 매도조건 만족 : ["+ market_name +"]");
                				   Thread.sleep(500);
                			   }
                  			   
								/*
								 * if((avg_buy_price*1.03) < trade_price) { // 1825 < 1580 order(market_name,
								 * 0.0, balance, "sell", "market"); // 시장가 매도
								 * logger.info("매수가 +3% 이상 매도조건 만족 : ["+ market_name +"]"); Thread.sleep(500); }
								 */
                  			   
                  			   if((avg_buy_price*0.98) >= trade_price) { // 1520 > 1580
                				   order(market_name, 0.0, balance, "sell", "market"); // 시장가 매도
                				   logger.info("-2.0% 손절 매도조건 만족 : ["+ market_name +"]");
                				   Thread.sleep(500);
                			   }
                  			   /*
							   if(formatedNow.indexOf(dateNow) != -1 && balance > 0.0) { // 다음날 8시 59분이 되면 다
								   order(market_name, 0.0, balance, "sell", "market"); // 시장가 매도
								   logger.info("8시 59분 전량 매도조건 만족 : ["+ market_name +"]"); 
								   Thread.sleep(500); 
							   }
							   */
                  		   }
            		   }
//            		   locked = aa.getString("locked"); // 주문 들어간 수량
                	}// for문 end
            	    if(formatedNow.indexOf(dateNow) != -1) { // 다음날 8시 59분이 되면 1분은 슬립
      				   Thread.sleep(60000);
      			    }
               } else if (mode.equals("mode3")) { // 나의 잔고 조회
            	   double currency_KRW = 0.0;
            	   JSONObject cc = result.getJSONObject(0);
            	   currency_KRW = Double.parseDouble(cc.getString("balance"));
            	   for(int i=0; i<result.length(); i++){
            		   JSONObject aa = result.getJSONObject(i);
            		   currency = aa.getString("currency"); //화폐를 의미하는 영문 대문자 코드
            		   balance = Double.parseDouble(aa.getString("balance")); // 보유 수량
            		   avg_buy_price = Double.parseDouble(aa.getString("avg_buy_price")); //평단가
            		   //locked = aa.getString("locked"); // 주문 들어간 수량
            		   if(currency_KRW < 5000) {
            			   if(avg_buy_price * balance > 5000 && !currency.equals("KRW") && !currency.equals("ETC") && !currency.equals("USDT") && !currency.equals("VTHO") && !currency.equals("APENFT")
                    				 && !currency.equals("SOLO") && !currency.equals("ETHW") && !currency.equals("ETHF") && !currency.equals("XCORE")) {
                  			   CoinProgramApplication.operate = false; // 매수 종목 코인 리스트 조회 동작 그만
                  			   break;
                  		   }
            		   } else {
            			   CoinProgramApplication.operate = true; // 매수 종목 코인 리스트 조회 동작 시작
            		   }
                	}
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void order(String market, double target, double price, String mode, String type) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	HashMap<String, String> params = new HashMap<>();
    	if(mode.equals("buy")) {
    		params.put("market", market);
    		params.put("side", "bid"); // bid : 매수, ask : 매도
    		params.put("ord_type", type); // limit : 지정가, price : 시장가 매수, market : 시장가 매도
    		if(type.equals("limit")) {
    			params.put("price", String.valueOf(Math.floor(price))); // 주문 가격 ------ 시장가 매수시 필수
        		params.put("volume", String.format("%.3f", price/target)); // 주문 수량 ------ 시장가 매도시 필수
    		} else if(type.equals("price")) {
    			params.put("price", String.valueOf(Math.floor(price))); // 주문 가격 ------ 시장가 매수시 필수
    		}
    	} else if (mode.equals("sell")) {
    		params.put("market", market);
    		params.put("side", "ask"); // bid : 매수, ask : 매도
    		params.put("ord_type", type); // limit : 지정가, price : 시장가 매수, market : 시장가 매도
    		if(type.equals("limit")) {
    			params.put("price", String.format("%.0f", target)); // 주문 가격 ------ 시장가 매수시 필수
        		params.put("volume", String.valueOf(Math.floor(price*100000)/100000.0)); // 주문 수량 ------ 시장가 매도시 필수
    		} else if(type.equals("market")) {
    			params.put("volume", String.valueOf(Math.floor(price*100000)/100000.0)); // 매도 수량 ------ 시장가 매도시 필수
    		}
    	}
    	
        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(serverUrl + "/v1/orders");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            request.setEntity(new StringEntity(new Gson().toJson(params)));

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println(EntityUtils.toString(entity, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    
    
    
}
