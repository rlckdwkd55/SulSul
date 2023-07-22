import request, { FAIL, SUCCESS, ACCESS_TOKEN } from "../Util/Request";

const OrderService = {
  postOrderItemList: async (data) => {
    try {
      const response = await request.post('', data);
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

export default OrderService;
