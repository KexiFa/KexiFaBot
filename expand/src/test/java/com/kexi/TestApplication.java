//package com.kexi;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.kexifa.entity.ElmCk;
//import com.kexifa.utils.HttpUtils;
//import lombok.Data;
//import lombok.SneakyThrows;
//import okhttp3.Response;
//import org.apache.http.Header;
//import org.junit.Test;
//import org.python.antlr.ast.Str;
//import org.python.util.PythonInterpreter;
//
//import javax.script.Invocable;
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * @author kexifa
// * @version 1.0
// * @date 2023/7/14 17:40
// */
//public class TestApplication {
//    String js = "function jj(e) {\n" +
//            "    function t(e, t) {\n" +
//            "        return e << t | e >>> 32 - t\n" +
//            "    }\n" +
//            "    function n(e, t) {\n" +
//            "        var n, r, o, i, a;\n" +
//            "        return o = 2147483648 & e,\n" +
//            "            i = 2147483648 & t,\n" +
//            "            a = (1073741823 & e) + (1073741823 & t),\n" +
//            "            (n = 1073741824 & e) & (r = 1073741824 & t) ? 2147483648 ^ a ^ o ^ i : n | r ? 1073741824 & a ? 3221225472 ^ a ^ o ^ i : 1073741824 ^ a ^ o ^ i : a ^ o ^ i\n" +
//            "    }\n" +
//            "    function r(e, r, o, i, a, u, s) {\n" +
//            "        return e = n(e, n(n(function (e, t, n) {\n" +
//            "            return e & t | ~e & n\n" +
//            "        }(r, o, i), a), s)),\n" +
//            "            n(t(e, u), r)\n" +
//            "    }\n" +
//            "    function o(e, r, o, i, a, u, s) {\n" +
//            "        return e = n(e, n(n(function (e, t, n) {\n" +
//            "            return e & n | t & ~n\n" +
//            "        }(r, o, i), a), s)),\n" +
//            "            n(t(e, u), r)\n" +
//            "    }\n" +
//            "    function i(e, r, o, i, a, u, s) {\n" +
//            "        return e = n(e, n(n(function (e, t, n) {\n" +
//            "            return e ^ t ^ n\n" +
//            "        }(r, o, i), a), s)),\n" +
//            "            n(t(e, u), r)\n" +
//            "    }\n" +
//            "    function a(e, r, o, i, a, u, s) {\n" +
//            "        return e = n(e, n(n(function (e, t, n) {\n" +
//            "            return t ^ (e | ~n)\n" +
//            "        }(r, o, i), a), s)),\n" +
//            "            n(t(e, u), r)\n" +
//            "    }\n" +
//            "    function u(e) {\n" +
//            "        var t, n = \"\", r = \"\";\n" +
//            "        for (t = 0; 3 >= t; t++)\n" +
//            "            n += (r = \"0\" + (e >>> 8 * t & 255).toString(16)).substr(r.length - 2, 2);\n" +
//            "        return n\n" +
//            "    }\n" +
//            "    var s, l, c, d, f, p, h, m, g, v;\n" +
//            "    for (v = function (e) {\n" +
//            "        for (var t, n = e.length, r = n + 8, o = 16 * ((r - r % 64) / 64 + 1), i = new Array(o - 1), a = 0, u = 0; n > u;)\n" +
//            "            a = u % 4 * 8,\n" +
//            "                i[t = (u - u % 4) / 4] = i[t] | e.charCodeAt(u) << a,\n" +
//            "                u++;\n" +
//            "        return a = u % 4 * 8,\n" +
//            "            i[t = (u - u % 4) / 4] = i[t] | 128 << a,\n" +
//            "            i[o - 2] = n << 3,\n" +
//            "            i[o - 1] = n >>> 29,\n" +
//            "            i\n" +
//            "    }(e = function (e) {\n" +
//            "        e = e.replace(/\\r\\n/g, \"\\n\");\n" +
//            "        for (var t = \"\", n = 0; n < e.length; n++) {\n" +
//            "            var r = e.charCodeAt(n);\n" +
//            "            128 > r ? t += String.fromCharCode(r) : r > 127 && 2048 > r ? (t += String.fromCharCode(r >> 6 | 192),\n" +
//            "                t += String.fromCharCode(63 & r | 128)) : (t += String.fromCharCode(r >> 12 | 224),\n" +
//            "                    t += String.fromCharCode(r >> 6 & 63 | 128),\n" +
//            "                    t += String.fromCharCode(63 & r | 128))\n" +
//            "        }\n" +
//            "        return t\n" +
//            "    }(e)),\n" +
//            "        p = 1732584193,\n" +
//            "        h = 4023233417,\n" +
//            "        m = 2562383102,\n" +
//            "        g = 271733878,\n" +
//            "        s = 0; s < v.length; s += 16)\n" +
//            "        l = p,\n" +
//            "            c = h,\n" +
//            "            d = m,\n" +
//            "            f = g,\n" +
//            "            p = r(p, h, m, g, v[s + 0], 7, 3614090360),\n" +
//            "            g = r(g, p, h, m, v[s + 1], 12, 3905402710),\n" +
//            "            m = r(m, g, p, h, v[s + 2], 17, 606105819),\n" +
//            "            h = r(h, m, g, p, v[s + 3], 22, 3250441966),\n" +
//            "            p = r(p, h, m, g, v[s + 4], 7, 4118548399),\n" +
//            "            g = r(g, p, h, m, v[s + 5], 12, 1200080426),\n" +
//            "            m = r(m, g, p, h, v[s + 6], 17, 2821735955),\n" +
//            "            h = r(h, m, g, p, v[s + 7], 22, 4249261313),\n" +
//            "            p = r(p, h, m, g, v[s + 8], 7, 1770035416),\n" +
//            "            g = r(g, p, h, m, v[s + 9], 12, 2336552879),\n" +
//            "            m = r(m, g, p, h, v[s + 10], 17, 4294925233),\n" +
//            "            h = r(h, m, g, p, v[s + 11], 22, 2304563134),\n" +
//            "            p = r(p, h, m, g, v[s + 12], 7, 1804603682),\n" +
//            "            g = r(g, p, h, m, v[s + 13], 12, 4254626195),\n" +
//            "            m = r(m, g, p, h, v[s + 14], 17, 2792965006),\n" +
//            "            p = o(p, h = r(h, m, g, p, v[s + 15], 22, 1236535329), m, g, v[s + 1], 5, 4129170786),\n" +
//            "            g = o(g, p, h, m, v[s + 6], 9, 3225465664),\n" +
//            "            m = o(m, g, p, h, v[s + 11], 14, 643717713),\n" +
//            "            h = o(h, m, g, p, v[s + 0], 20, 3921069994),\n" +
//            "            p = o(p, h, m, g, v[s + 5], 5, 3593408605),\n" +
//            "            g = o(g, p, h, m, v[s + 10], 9, 38016083),\n" +
//            "            m = o(m, g, p, h, v[s + 15], 14, 3634488961),\n" +
//            "            h = o(h, m, g, p, v[s + 4], 20, 3889429448),\n" +
//            "            p = o(p, h, m, g, v[s + 9], 5, 568446438),\n" +
//            "            g = o(g, p, h, m, v[s + 14], 9, 3275163606),\n" +
//            "            m = o(m, g, p, h, v[s + 3], 14, 4107603335),\n" +
//            "            h = o(h, m, g, p, v[s + 8], 20, 1163531501),\n" +
//            "            p = o(p, h, m, g, v[s + 13], 5, 2850285829),\n" +
//            "            g = o(g, p, h, m, v[s + 2], 9, 4243563512),\n" +
//            "            m = o(m, g, p, h, v[s + 7], 14, 1735328473),\n" +
//            "            p = i(p, h = o(h, m, g, p, v[s + 12], 20, 2368359562), m, g, v[s + 5], 4, 4294588738),\n" +
//            "            g = i(g, p, h, m, v[s + 8], 11, 2272392833),\n" +
//            "            m = i(m, g, p, h, v[s + 11], 16, 1839030562),\n" +
//            "            h = i(h, m, g, p, v[s + 14], 23, 4259657740),\n" +
//            "            p = i(p, h, m, g, v[s + 1], 4, 2763975236),\n" +
//            "            g = i(g, p, h, m, v[s + 4], 11, 1272893353),\n" +
//            "            m = i(m, g, p, h, v[s + 7], 16, 4139469664),\n" +
//            "            h = i(h, m, g, p, v[s + 10], 23, 3200236656),\n" +
//            "            p = i(p, h, m, g, v[s + 13], 4, 681279174),\n" +
//            "            g = i(g, p, h, m, v[s + 0], 11, 3936430074),\n" +
//            "            m = i(m, g, p, h, v[s + 3], 16, 3572445317),\n" +
//            "            h = i(h, m, g, p, v[s + 6], 23, 76029189),\n" +
//            "            p = i(p, h, m, g, v[s + 9], 4, 3654602809),\n" +
//            "            g = i(g, p, h, m, v[s + 12], 11, 3873151461),\n" +
//            "            m = i(m, g, p, h, v[s + 15], 16, 530742520),\n" +
//            "            p = a(p, h = i(h, m, g, p, v[s + 2], 23, 3299628645), m, g, v[s + 0], 6, 4096336452),\n" +
//            "            g = a(g, p, h, m, v[s + 7], 10, 1126891415),\n" +
//            "            m = a(m, g, p, h, v[s + 14], 15, 2878612391),\n" +
//            "            h = a(h, m, g, p, v[s + 5], 21, 4237533241),\n" +
//            "            p = a(p, h, m, g, v[s + 12], 6, 1700485571),\n" +
//            "            g = a(g, p, h, m, v[s + 3], 10, 2399980690),\n" +
//            "            m = a(m, g, p, h, v[s + 10], 15, 4293915773),\n" +
//            "            h = a(h, m, g, p, v[s + 1], 21, 2240044497),\n" +
//            "            p = a(p, h, m, g, v[s + 8], 6, 1873313359),\n" +
//            "            g = a(g, p, h, m, v[s + 15], 10, 4264355552),\n" +
//            "            m = a(m, g, p, h, v[s + 6], 15, 2734768916),\n" +
//            "            h = a(h, m, g, p, v[s + 13], 21, 1309151649),\n" +
//            "            p = a(p, h, m, g, v[s + 4], 6, 4149444226),\n" +
//            "            g = a(g, p, h, m, v[s + 11], 10, 3174756917),\n" +
//            "            m = a(m, g, p, h, v[s + 2], 15, 718787259),\n" +
//            "            h = a(h, m, g, p, v[s + 9], 21, 3951481745),\n" +
//            "            p = n(p, l),\n" +
//            "            h = n(h, c),\n" +
//            "            m = n(m, d),\n" +
//            "            g = n(g, f);\n" +
//            "    return (u(p) + u(h) + u(m) + u(g)).toLowerCase()\n" +
//            "}\n" +
//            "\n" +
//            "function map(mh5tk, ts, data) {\n" +
//            "    var e = (mh5tk + \"&\" + ts + \"&\" + '12574478' + \"&\" + data)\n" +
//            "    return jj(e)\n" +
//            "}\n";
//
//
//
//    public static ScriptEngine engine;
//    @SneakyThrows
//    @Test
//    public void test1(){
//
//        ScriptEngineManager sem = new ScriptEngineManager();
//        engine=sem.getEngineByName("javascript");
//
//        String ck="_m_h5_tk=fbe196d503640ff454c2a4662ccaf50f_1690302694843; _m_h5_tk_enc=0f6a3e631653c9c41fd97a0d6f15a0bc; isg=BJaWPa1c7t9cxtp4zM-B1lKd7UiYN9pxdsPtFwD_gnkUwzZdaMcqgfyjXdkv8NKJ; l=fBTjIWH4NluzjG6SBOfanurza77OSCdYYuPzaNbMi9fPsjCe5CJVW1_F8iLwC31RFsK9R3kdpol9BeYBmn20_T7t-H3LzzkmndLHR35..; tfstk=cJV1Bb6rcZ_NR9nehc_UdZzLFFGcZf5j-9mu5gFzznADK0q1iWKrVmzUfLHtt21..; _tb_token_=e9aa73bee8573; cookie2=1e58bf419aa4d6c0cf2614b03fb3ae99; csg=6639ed17; munb=2214899242460; sgcookie=W100N5V5n3wOnyJYGoMqUcu1iS0BQOqWwcSFdiAIkJuAbykIj2oooRa8rQqQG12gBsyo3lkdBf2ZQHKqzT2DZgGy9vmMZjP1FmUPFMiqkFbVwc8%3D; t=38467a71a5a0816ab44876eda88594d2; unb=2214899242460; x5check_ele=phbcavL%2FovgijoDc1cbB4ZP3%2BpgC1mmUZReFHHp4jls%3D; SID=MWU1OGJmNDE5YWE0ZDZjMGNmMjYxNGIwM2ZiM2FlOTnHwq94Ns-BW9YgFKkcFJUL; USERID=1000320080455; UTUSER=1000320080455; cna=oepEHd3c6m0CAduB1VWxqro6";
//        engine.eval(js);
//        Invocable jsInvoke = (Invocable) engine;
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("templateIds","[\"1404\"]");
//
////        System.out.println(jsonObject.toJSONString());
//        String ts = String.valueOf(System.currentTimeMillis());
//        System.out.println(System.currentTimeMillis());
//        Object sign = jsInvoke.invokeFunction("map", new Object[] {"a8b654ea8b2d8897556edb7eed592e4e",ts, jsonObject});
//        System.out.println(sign);
////
//////        String getUrl = "https://mtop.ele.me/h5/mtop.koubei.interaction.center.common.queryintegralproperty.v2/1.0/?jsv=2.7.0&appKey=12574478&t=" + ts + "&sign=" + sign + "&api=mtop.koubei.interaction.center.common.queryintegralproperty.v2&v=1.0&ecode=1&type=json&valueType=string&needLogin=true&LoginRequest=true&dataType=jsonp";
////        String getUrl="https://shopping.ele.me/h5/mtop.alsc.user.session.ele.check/1.0/?jsv=2.7.0&appKey=12574478&t=1688835243268&sign=8c796345adeaba644660a707b47593da&api=mtop.alsc.user.session.ele.check&v=1.0&dataType=json&timeout=5000&subDomain=shopping&mainDomain=ele.me&H5Request=true&pageDomain=ele.me&type=originaljson&data=%7B%7D";
////        System.out.println(getUrl);
//////        String getUrl = "https://shopping.ele.me/h5/mtop.alsc.user.session.ele.check/1.0/?jsv=2.7.0&appKey=12574478&t=1688835243268&sign=8c796345adeaba644660a707b47593da&api=mtop.alsc.user.session.ele.check&v=1.0&dataType=json&timeout=5000&subDomain=shopping&mainDomain=ele.me&H5Request=true&pageDomain=ele.me&type=originaljson&data=%7B%7D";
////        System.out.println(getUrl);
////        String param = "data=%7B%22templateIds%22%3A%22%5B%5C%221404%5C%22%5D%22%7D";
//////        Response response = HttpUtils.postEnvElm(getUrl, param, ck);
////        HashMap<String,String > map = new HashMap<>();
////        Header[] headers = HttpUtils.getReturnHeader(getUrl, ck);
////        //_m_h5_tk=3f5137eab998773b887061ff898e38d0_1689350076098;
////        String _m_h5_tk = headers[0].getValue().split(";")[0]+";";
////        String _m_h5_tk_enc = headers[1].getValue().split(";")[0]+";";
////
////
////
////        String pattern = "_m_h5_tk=[0-9a-f]+_[0-9]+;";
////        String _m_h5_tk_ck = null;
////        Matcher matcher = Pattern.compile(pattern).matcher(ck);
////        if (matcher.find()) {
////            _m_h5_tk_ck = ck.substring(matcher.start(0), matcher.end(0));
////            ck = ck.replace(_m_h5_tk_ck,"");
////        }
////
////        String pattern2 = "_m_h5_tk_enc=[0-9a-f]+;";
////        String _m_h5_tk_enc_ck= null;
////        Matcher matcher2 = Pattern.compile(pattern2).matcher(ck);
////        if (matcher2.find()) {
////            _m_h5_tk_enc_ck = ck.substring(matcher.start(0), matcher.end(0));
////            ck = ck.replace(_m_h5_tk_enc_ck,"");
////        }
////        System.out.println("_m_h5_tk_enc_ck"+_m_h5_tk_enc_ck);
////        System.out.println(ck);
////
////        String cookie = _m_h5_tk+_m_h5_tk_enc+ck;
////        System.out.println(_m_h5_tk);
////        System.out.println(cookie);
////        String mh5tk = _m_h5_tk.substring(9,_m_h5_tk.length()-1);
////        System.out.println(mh5tk);
////        String ts = String.valueOf(System.currentTimeMillis());
////        JSONObject jsonObject = new JSONObject();
////        jsonObject.put("templateIds","[1404]");
////        System.out.println("jsonObject=="+ jsonObject);
////        Object sign = jsInvoke.invokeFunction("map", new Object[] {mh5tk,ts, jsonObject});
////        System.out.println(sign);
////        System.out.println(_m_h5_tk_enc);
////        map.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
////        System.out.println(s);
//
////        String mh5tk = "a64d986e6d787914fa27ce576e576152_1689309745227";
////        String ts = String.valueOf(System.currentTimeMillis());
////        JSONObject jsonObject = new JSONObject();
////        jsonObject.put("templateIds","[\"1404\"]");
////        String cookie = "";
////        Object sign = jsInvoke.invokeFunction("map", new Object[] {sign_f,ts, jsonObject.toJSONString()});
////        System.out.println(sign);
//
//
//
////
////        String url = "https://running-game.ele.me/h5/mtop.koubei.interaction.center.common.queryintegralproperty.v2/1.0/jsv=2.7.0&appKey=12574478&t=" + ts +"&sign=" + sign + "&api=mtop.koubei.interaction.center.common.queryintegralproperty.v2&v=1.0&ecode=1&type=json&valueType=string&needLogin=true&LoginRequest=true&dataType=jsonp";
////
////        System.out.println(url);
////        HashMap<String,String> maps = new HashMap<String,String>();
////        maps.put("Host","mtop.ele.me");
////        maps.put("Accept","application/json");
////        maps.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
////        maps.put("Content-type","application/x-www-form-urlencoded");
////        maps.put("Origin","https://tb.ele.me");
////        maps.put("Sec-Fetch-Site","same-site");
////        maps.put("Sec-Fetch-Mode","cors");
////        maps.put("Sec-Fetch-Dest","empty");
////        maps.put("Referer","https://tb.ele.me/wow/alsc/mod/3fe8408d9ba38d4726448a87?spm-pre=a2ogi.bx828379.0.0&spm=a13.b_activity_kb_m69301.0.0");
////        maps.put("Accept-Encoding","gzip, deflate, br");
////        maps.put("Accept-Language","zh-CN,zh;q=0.9");
////        maps.put("Cookie",cookie);
////
////        HashMap<String,String>  param = new HashMap<>();
////        param.put("data","%7B%22templateIds%22%3A%22%5B%5C%221404%5C%22%5D%22%7D");
////        System.out.println(url);
////        System.out.println(maps);
////        String post = HttpUtils.post(url, maps, param);
////        System.out.println(post);
//    }
//
//    @Test
//    public  void  test(){
//        String url = "http://localhost:8080/ejjd-web/api/consultation/getTraceCode?sign=6cdc57ee4d64cf2945a03968d802a30c9278afb8&timeStamp=1690446119";
//        Map<String,String> map = new HashMap<>();
//        map.put("uploader","何德庆");
//        String post = HttpUtils.post(url, map);
//        System.out.println(post);
//    }
//}
