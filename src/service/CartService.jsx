import request, { FAIL, SUCCESS, ACCESS_TOKEN } from "../Util/Request";

const CartService = {
  postCartList: async (data) => {
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
  },
  postDeleteCartItem: async (data) => {
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
  },
};

export default CartService;
