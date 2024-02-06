package info.bitrich.xchangestream.binance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import org.knowm.xchange.binance.dto.marketdata.BinanceAggTrades;

public class TradeBinanceWebsocketTransaction extends ProductBinanceWebSocketTransaction {

  private final BinanceRawTrade rawTrade;

  public TradeBinanceWebsocketTransaction(
      @JsonProperty("e") String eventType,
      @JsonProperty("E") String eventTime,
      @JsonProperty("s") String symbol,
      @JsonProperty("t") long tradeId,
      @JsonProperty("p") BigDecimal price,
      @JsonProperty("q") BigDecimal quantity,
      @JsonProperty("b") long buyerOrderId,
      @JsonProperty("a") long sellerOrderId,
      @JsonProperty("T") long timestamp,
      @JsonProperty("m") boolean buyerMarketMaker,
      @JsonProperty("M") boolean ignore) {
    super(eventType, eventTime, symbol);

    rawTrade =
        new BinanceRawTrade(
            eventType,
            eventTime,
            symbol,
            tradeId,
            price,
            quantity,
            buyerOrderId,
            sellerOrderId,
            timestamp,
            buyerMarketMaker,
            ignore);
  }

  public BinanceRawTrade getRawTrade() {
    return rawTrade;
  }
  public BinanceAggTrades getAggTrade() {
    final BinanceAggTrades binanceAggTrades = new BinanceAggTrades(
        rawTrade.getTradeId(),
        rawTrade.getPrice(),
        rawTrade.getQuantity(),
        rawTrade.getBuyerOrderId(),
        rawTrade.getSellerOrderId(),
        rawTrade.getTimestamp(),
        rawTrade.isBuyerMarketMaker(),
        rawTrade.isIgnore()
    );
    return binanceAggTrades;
  }
}
