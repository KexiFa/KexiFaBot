package com.kexifa.service;

import okhttp3.Response;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/6/30 16:03
 */
public interface ILoginService {
    Response dologin(String ck, String qq);
}
