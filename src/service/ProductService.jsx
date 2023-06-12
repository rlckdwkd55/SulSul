import request, { FAIL, SUCCESS, ACCESS_TOKEN } from "../Util/Request";

const ProductService = {
  getMainItemList: async () => {
    try {
      const response = await request.get(`/main`);
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
  postProductName: async (data) => {
    try {
      const response = await request.post('/product/productNameList', data);
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

export default ProductService;
