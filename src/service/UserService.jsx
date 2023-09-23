import request, { FAIL, SUCCESS, ACCESS_TOKEN } from "../Util/Request";

const UserService = {
  //User My Information
  postOrderData: async () => {
    const userId = {
      userId: "user01"
    };
    try {
      const response = await request.post(`/myPage/orderList`, userId);
      if (request.isSuccess(response)) {
        return {
          ...response,
          status: SUCCESS
        };
      }
      return { status: FAIL };
    } catch (error) {
      return {
        status: FAIL
      };
    }
  },
  postUserInfo: async (userEmail) => {
    const data = {
      email: userEmail
    };
    try {
      const response = await request.post(`/member/info`, data);
      if (request.isSuccess(response)) {
        return {
          ...response,
          status: SUCCESS
        };
      }
      return { status: FAIL };
    } catch (error) {
      return {
        status: FAIL
      };
    }
  },
};

export default UserService;
