import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


class StockData {
    private String date;
    private String open;
    private String close;
    private String high;
    private String low;

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }

    public void setOpen(String open) {
        this.open = open;
    }
    public String getOpen() {
        return this.open;
    }

    public void setClose(String close) {
        this.close = close;
    }
    public String getClose() {
        return this.close;
    }

    public void setHigh(String high) {
        this.high = high;
    }
    public String getHigh() {
        return this.high;
    }

    public void setLow(String low) {
        this.low = low;
    }
    public String getLow() {
        return this.low;
    }
}

class Stock {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<StockData> data;

    public void setPage(int page) {
        this.page = page;
    }
    public int getPage() {
        return this.page;
    }

    public void setPerPage(int per_page) {
        this.per_page = per_page;
    }
    public int getPerPage() {
        return this.per_page;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return this.total;
    }

    public void setTotalPages(int total_pages) {
        this.total_pages = total_pages;
    }
    public int getTotalPages() {
        return this.total_pages;
    }

    public void setData(List<StockData> data) {
        this.data = data;
    }
    public List<StockData> getData() {
        return this.data;
    }
}

public class Solution {

    static StringBuffer getContent(InputStream iStream) {
        String inputLine;
        StringBuffer content = new StringBuffer();
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(iStream));
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        }
        catch(IOException ioExc)
        {
            System.out.println(ioExc.toString());
        }
        return content;
    }

    static int getTotalPages() throws Exception {
        URL url = new URL("https://jsonmock.hackerrank.com/api/stocks");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        StringBuffer jsonRes = getContent(con.getInputStream());
        Gson gson = new Gson();
        Stock stock = gson.fromJson(jsonRes.toString(), Stock.class);
        con.disconnect();
        return stock.getTotalPages();
    }

    static String fetchPricesFromURL(int totalPages, String date) throws Exception {
       for(int i=1; i<=totalPages; ++i) {

           URL searchUrl = new URL("https://jsonmock.hackerrank.com/api/stocks/search?date="+date+"&page="+i);
           HttpURLConnection pageCon = (HttpURLConnection) searchUrl.openConnection();
           pageCon.setRequestMethod("GET");
           Gson gson = new Gson();

            StringBuffer stockPriceForDate = getContent(pageCon.getInputStream());
            Stock stockForDate = gson.fromJson(stockPriceForDate.toString(), Stock.class);
            if(stockForDate != null)
            {
                if(stockForDate.getData().size() != 0) {
                    pageCon.disconnect();
                    StockData stockData = stockForDate.getData().get(0);
                    return stockData.getDate()+" "+stockData.getOpen()+" "+stockData.getClose();
                }
            }
           pageCon.disconnect();
        }
        return "";
    }

    /*
     * Complete the function below.
     */
    static void openAndClosePrices(String firstDate, String lastDate, String weekDay) {
        try{
            SimpleDateFormat simpleFormatter = new SimpleDateFormat("d-MMMM-yyyy");
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat("EEEE");
            Date startDate = simpleFormatter.parse(firstDate);
            Date endDate = simpleFormatter.parse(lastDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int skip = 1;

            while(startDate.before(endDate))
            {
                if(simpleDayFormat.format(startDate).equals(weekDay))
                {
                    String str = fetchPricesFromURL(getTotalPages(), simpleFormatter.format(startDate));
                    if(str.length() != 0)
                    {
                        System.out.println(str);
                    }
                    skip = 7;
                }
                calendar.add(Calendar.DATE, skip);
                startDate = calendar.getTime();
            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String _firstDate;
        try {
            _firstDate = in.nextLine();
        } catch (Exception e) {
            _firstDate = null;
        }

        String _lastDate;
        try {
            _lastDate = in.nextLine();
        } catch (Exception e) {
            _lastDate = null;
        }

        String _weekDay;
        try {
            _weekDay = in.nextLine();
        } catch (Exception e) {
            _weekDay = null;
        }

        openAndClosePrices(_firstDate, _lastDate, _weekDay);

    }
}