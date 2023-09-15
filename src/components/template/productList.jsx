import { useState, useEffect } from 'react';
import ProductService from '../../service/ProductService';
import Product from "../atoms/product";
import styled from 'styled-components';

const ListWrap = styled.div`
  
`;

const ProductList = (props) => {
  const [productList, setProductList] = useState([]);

  useEffect(()=>{
    async function getProduct(param, isSearch) {
      if (!isSearch) {
        if (/^newItems$|^bestItems$/.test(param)) {
          const response = await ProductService.getMainItemList();
    
          if (response.status === "success") {
            setProductList(response.data["newItems"]);
            if (props.setPrdCnt) props.setPrdCnt(response.data["newItems"].length);
          }
        } else {
          const data = {
            'categoryNo': param
          }
  
          const response = await ProductService.postCategoryList(data);
    
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
      {productList.map((item) => {
        return <Product key={item.PRODUCT_NO} prdNo={item.PRODUCT_NO} prdName={item.PRODUCT_NAME} prdPrice={item.PRODUCT_PRICE} imgPath={"/images/product/product01.jpg"}/>
      })}
    </ListWrap>
  )
}

export default ProductList;