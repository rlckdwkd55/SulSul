import request, { FAIL, SUCCESS, ACCESS_TOKEN } from "../Util/Request";

const UserService = {
  //User My Information
  postOrderData: async () => {
    try {
      const response = await request.post(`/myPage/orderList`);
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
  }
};

export default UserService;
