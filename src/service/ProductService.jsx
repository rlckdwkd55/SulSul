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
  },
  postProductList: async (data) => {
    try {
      const response = await request.post('/product/productList', data);
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
  postCategoryList: async (data) => {
    try {
      const response = await request.post('/product/categoryList', data);
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
  postProductDetail: async (data) => {
    try {
      const response = await request.post('/product/productDetail', data);
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
  getAllProductList: async () => {
    try {
      const response = await request.get('/product/allProductList');
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

export default ProductService;
