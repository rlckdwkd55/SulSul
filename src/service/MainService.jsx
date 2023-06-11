import request, { FAIL, SUCCESS, ACCESS_TOKEN } from "../Util/Request";

const MainService = {
  // Main best, new items
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
  }
};

export default MainService;
