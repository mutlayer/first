<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- TradingView Widget BEGIN -->
<script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
<script type="text/javascript">
new TradingView.widget({
	"width": 998,
	"height": 610,
	"symbol": "NASDAQ:AAPL",
	"interval": "D",
	"timezone": "exchange",
	"theme": "White",
	"style": "0",
	"toolbar_bg": "#f1f3f6",
	"withdateranges": true,
	"allow_symbol_change": true,
	"save_image": false,
	"details": true,
	"hotlist": true,
	"calendar": true,
	"news": [ "headlines" ],
	"hideideas": true
});

function test_click(){
	var price="0";
	var ei = document.getElementsByClassName("dl-header-price")[0];
	alert(ei);
	if(ei != null){
		price=el.innerText || el.textContent;

	}
	alert(price);
}
</script>
<!-- TradingView Widget END -->


<input type="button" id="btn_test" name="btn_test" onclick="test_click()" value="TEST">


