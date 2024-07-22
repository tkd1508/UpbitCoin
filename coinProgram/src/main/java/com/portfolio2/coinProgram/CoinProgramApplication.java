package com.portfolio2.coinProgram;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.portfolio2.coinProgram.common.util.UISMap;
import com.portfolio2.coinProgram.upbit.Account;
import com.portfolio2.coinProgram.upbit.MarketSee;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class CoinProgramApplication {

	public static boolean operate = true;
	public static boolean LarryWilliams = true;
	private static Logger logger = (Logger) LoggerFactory.getLogger(CoinProgramApplication.class);
	
	public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException, InterruptedException {
		SpringApplication.run(CoinProgramApplication.class, args);
		
		System.out.println("================= 시작 =================");
		
		MarketSee marketSee = new MarketSee();
		Account upbitWallet = new Account();
		List<UISMap> selectCoinList = new ArrayList<UISMap>();
		
		while(true) {
			if(operate) {
				//매수 종목 코인 리스트 조회
				selectCoinList = marketSee.marketSee(); 
				//upbitWallet.myMoney(null, "mode3");
				/*
				if(selectCoinList.size() > 0 && LarryWilliams) {
					//잔고 조회 후 시장가 매수 - 래리 윌리엄스 매수
					upbitWallet.myMoney(selectCoinList, "mode1");
				} else if(selectCoinList.size() > 0 && !LarryWilliams) {
					//잔고 조회 후 시장가 매수 - 분봉 초단타 매수
					upbitWallet.myMoney(selectCoinList, "mode4");
				}
				*/
				//잔고 조회 후 시장가 매수 - 분봉 초단타 매수
				upbitWallet.myMoney(selectCoinList, "mode4");
			} else {
				Thread.sleep(60000);
				//매도 종목 코인 리스트 조회 후 시장가 매도
				upbitWallet.myMoney(null, "mode2");
				//잔고 조회 후 다시 동작 여부 확인
				upbitWallet.myMoney(null, "mode3");
				logger.info("======== 매도 진행중 ========");
			}
		}
    } 
}
