import { useState, useEffect } from 'react';
import ProductService from '../../service/ProductService';
import Product from "../atoms/product";
import styled from 'styled-components';

const ListWrap = styled.div`
  margin-left: -20px;
`;

const ProductList = (props) => {
  const [productList, setProductList] = useState([]);

  useEffect(()=>{
    async function getProduct(param, isSearch) {
      if (!isSearch) {
        if (/^newItems$/.test(param)) {
          const response = await ProductService.getMainItemList();
    
          if (response.status === "success") {
            setProductList(response.data["newItems"]);
            if (props.setPrdCnt) props.setPrdCnt(response.data["newItems"].length);
          }
        } else {
          let response;
          if (param === '0') {
            response = await ProductService.getAllProductList();
          } else {
            const data = {
              'categoryNo': param
            }
    
            response = await ProductService.postCategoryList(data);
          }
          
          if (response.status === "success") {
            setProductList(response.data);
            if (props.setPrdCnt) props.setPrdCnt(response.data.length);
          }
        }
      } else {
        const data = {
          'requestString': param,
          'page': "1"
        }

        const response = await ProductService.postProductList(data);
  
        if (response.status === "success") {
          setProductList(response.data);
          if (props.setPrdCnt) props.setPrdCnt(response.data.length);
        }
      }
    }

    if (props.itemKey) {
      getProduct(props.itemKey);
    } else if (props.searchWord) {
      getProduct(props.searchWord, true);
    }
  }, [props.itemKey]);

  return(
    <ListWrap>
      {productList && productList.length > 0 ? (
        productList.map((item) => {
          return <Product key={item.productNo} prdNo={item.productNo} prdName={item.productName} prdPrice={item.productPrice} imgPath={"/images/product/cate0" + item.categoryNo + "/0" + item.categoryNo + item.productNo + "_Image.jpg"}/>
        }))
        : (
          <div style={{margin: '10px 0 0 20px'}}>등록 된 상품이 없습니다.</div>
        )
      }
    </ListWrap>
  )
}

export default ProductList;