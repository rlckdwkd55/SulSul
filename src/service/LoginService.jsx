import request, { FAIL, SUCCESS, ACCESS_TOKEN } from "../Util/Request";

const LoginService = {
  postLogin: async (data) => {
    try {
      const response = await request.post('/login', data);
      if (request.isSuccess(response)) {
        return {
          ...response,
          status: SUCCESS
        }
      }
    } catch (error) {
      return {
        status: FAIL
      }
    }
  },
  postJoin: async (userInfo) => {
    try {
      const response = await request.post('/join', userInfo);
      if (request.isSuccess(response)) {
        return {
          ...response,
          status: SUCCESS
        }
      }
    } catch (error) {
      return {
        status: FAIL
      }
    }
  },
  postCheckInfo: async (url, data) => {
    try{
      const response = await request.pose(url, data);
      if (request.isSuccess(response)) {
        return {
          ...response,
          status: SUCCESS
        }
      }
    } catch (error) {
      return {
        status: FAIL
      }
    }
  },
  getSnsLogin: async (type) => {
    const url = {
      'naver': '/naver/login/url',
      'kakao': '/kakao/login/url'
    }
    try{
      const response = await request.get(url[type]);
      if (request.isSuccess(response)) {
        return {
          ...response,
          status: SUCCESS
        }
      }
    } catch (error) {
      return {
        status: FAIL
      }
    }
  },
  getTokenRequest: async (type, code) => {
    const url = {
      'naver': '/naver/login/toekn/request',
      'kakao': '/kakao/login/url'
    }
    try{
      const response = await request.get(url[type]+'?code='+code);
      if (request.isSuccess(response)) {
        return {
          ...response,
          status: SUCCESS
        }
      }
    } catch (error) {
      return {
        status: FAIL
      }
    }
  },
  
};

export default LoginService;
