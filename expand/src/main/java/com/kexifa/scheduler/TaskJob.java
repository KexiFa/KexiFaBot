package com.kexifa.scheduler;

import com.kexifa.utils.HttpUtils;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/24 8:47
 */
@Component("taskJob")
public class TaskJob {
    private final String  advUrl = "https://mapi.xiaotucc.com/advert/white_list/perAd";
    private  final  String takeUrl = "https://mapi.xiaotucc.com/user/lottery/take";

    @SneakyThrows
    @Scheduled(cron = "0 10-20/1 10 * * ?")
    public void first() {
        String takeParam = "uid=3256357&timestamp=1690157158&token=BD354E7052A591FC18C73794ED3D03CF98EC1F3E";
        String advParam = "position=27&uid=3256357&timestamp=1690157123&token=6F1E10870B675898BBD2EEA312964A39C5154353";
        Response response = HttpUtils.postXiaoTuAd(advUrl, advParam);
        Response res =  HttpUtils.postXiaoTuTake(takeUrl, takeParam);
    }
    @SneakyThrows
    @Scheduled(cron = "0 21-31/1 8 * * ?")
    public void second() {
        String takeParam = "uid=3452969&timestamp=1689779316&token=27BA36AAC4AF74572912ADBC6019A4F9650CD660";
//        String advParam = "position=27&uid=3256357&timestamp=1690157123&token=6F1E10870B675898BBD2EEA312964A39C5154353";
//        Response response = HttpUtils.postXiaoTuAd(advUrl, advParam);
        Response res =  HttpUtils.postXiaoTuTake(takeUrl, takeParam);
    }
    @SneakyThrows
    @Scheduled(cron = "0 22-32/1 11 * * ?")
    public void third() {
        String takeParam = "uid=3456601&timestamp=1690162459&token=7F71CAC0C4C8AB4FFBE920F9672C3D6C4467E5C6";
        String advParam = "position=27&uid=3456601&timestamp=1690162418&token=D846F9963794BD7492C61817C974E7F6C17FF203";
        Response response = HttpUtils.postXiaoTuAd(advUrl, advParam);
        Response res =  HttpUtils.postXiaoTuTake(takeUrl, takeParam);
    }
    @SneakyThrows
    @Scheduled(cron = "0 34-44/1 12 * * ?")
    public void four() {
        String takeParam = "uid=3456636&timestamp=1690162685&token=F3D552D2F208A237E26629FCFDEEC70302C3B6FC";
//        String advParam = "position=27&uid=3256357&timestamp=1690157123&token=6F1E10870B675898BBD2EEA312964A39C5154353";
//        Response response = HttpUtils.postXiaoTuAd(advUrl, advParam);
        Response res =  HttpUtils.postXiaoTuTake(takeUrl, takeParam);
    }
    @SneakyThrows
    @Scheduled(cron = "0 31-41/1 13 * * ?")
    public void five() {
        String takeParam = "uid=3473104&timestamp=1690162873&token=94C89A23A004F97D96B1135566998CB3ED34E4DE";
        String advParam = "position=27&uid=3473104&timestamp=1690162834&token=865830B1DC91D24FF26CF7D1180843998DA4C8F3";
        Response response = HttpUtils.postXiaoTuAd(advUrl, advParam);
        Response res =  HttpUtils.postXiaoTuTake(takeUrl, takeParam);
    }


    @SneakyThrows
    @Scheduled(cron = "0 20-30/1 14 * * ?")
    public void sive() {
        String takeParam = "uid=3473113&timestamp=1690163189&token=BBA9D18C34AC21A449CB4673CE7BDC5F8ACAE7B7";
//        String advParam = "position=27&uid=3256357&timestamp=1690157123&token=6F1E10870B675898BBD2EEA312964A39C5154353";
//        Response response = HttpUtils.postXiaoTuAd(advUrl, advParam);
        Response res =  HttpUtils.postXiaoTuTake(takeUrl, takeParam);
    }

    @SneakyThrows
    @Scheduled(cron = "0 26-36/1 15 * * ?")
    public void seven() {
        String takeParam = "uid=3473126&timestamp=1690163366&token=BBB398402271DAAAA4F8607E283BE283FD1FDC33";
        String advParam = "position=27&uid=3473126&timestamp=1690163315&token=D92781FC4AEBF92FA041EE4C063C8B68B4288277";
        Response response = HttpUtils.postXiaoTuAd(advUrl, advParam);
        Response res =  HttpUtils.postXiaoTuTake(takeUrl, takeParam);
    }
}
