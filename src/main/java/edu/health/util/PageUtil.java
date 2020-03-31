package edu.health.util;

public class PageUtil {

    //分页大小
    private int size;
    //共计多少条
    private int count;
    //第几页
    private int p;
    //基本url
    private String url;

    public PageUtil(int size, int count, int p,String  url) {
        this.size = size;
        this.count = count;
        this.p = p;
        this.url = url;
    }

    public  String page(){
        int pageCount = (int) Math.ceil( (float)count/(float)size );
        String str = "";
        str += "<div class='page'>";
        for(int i = p-5 ; i < p ; i ++){
            if(i <1){
                continue;
            }else{
                str += "<a class='' href='"+shortUrl(i)+"'> "+ i +"</a>";
            }
        }
        str += "<a class='on' href='"+shortUrl(p)+"'> "+ p +"</a>";
        for(int i = p + 1 ; i < (p+5) && i <= pageCount ; i ++){
            str += "<a class='' href='"+shortUrl(i)+"'> "+ (i) +"</a>";
        }
        str += "</div>";
        return str;
    }


    private String shortUrl(int index){
        return this.url + "&p=" + index;
    }

    @Override
    public String toString() {


        return "PageUtil{" +
                "size=" + size +
                ", count=" + count +
                ", p=" + p +
                '}';
    }

    public static void main(String[] args){
        PageUtil pageUtil = new PageUtil(10,109, 2,"/blog/index?");
        System.out.println(pageUtil.page());
    }
}
