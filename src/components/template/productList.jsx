import { useState, useEffect } from 'react';
import ProductService from '../../service/ProductService';
import Product from "../atoms/product";
import styled from 'styled-components';

const ListWrap = styled.div`
  display: flex;
  flex-wrap: wrap;
  width: calc(100% + 20px);
  justify-content: space-between;
`;

const ProductList = (props) => {
  const [productList, setProductList] = useState([]);

  useEffect(()=>{
    async function getProduct(itemKey) {
      // if (/^newItems$|^bestItems$/.test(itemKey)) {
        // const response = await ProductService.getMainItemList();
  
        // if (response.status === "success") {
        //   setProductList(response.data["newItems"]);
        //   console.log(response.data);
        // }
      // } else {
        // const response = await ProductService.getProductList();
  
        // if (response.status === "success") {
        //   setProductList(response.data["???"]);
        //   console.log(response.data);
      // }
      setProductList([
        {
            "PRODUCT_NO": 1,
            "CATEGORY_NO": 1,
            "PRODUCT_NAME": "제품1",
            "PRODUCT_PRICE": 15000,
            "PRODUCT_AMOUNT": 1,
            "PRODUCT_STATUS": "Y",
            "PRODUCT_INFO": "설명1",
            "PRODUCT_COUNT": 1,
            "PAYMENT_COUNT": 1
        },
        {
            "PRODUCT_NO": 3,
            "CATEGORY_NO": 2,
            "PRODUCT_NAME": "제품3",
            "PRODUCT_PRICE": 11000,
            "PRODUCT_AMOUNT": 2,
            "PRODUCT_STATUS": "Y",
            "PRODUCT_INFO": "설명3",
            "PRODUCT_COUNT": 842,
            "PAYMENT_COUNT": 49
        },
        {
            "PRODUCT_NO": 2,
            "CATEGORY_NO": 2,
            "PRODUCT_NAME": "제품2",
            "PRODUCT_PRICE": 11000,
            "PRODUCT_AMOUNT": 2,
            "PRODUCT_STATUS": "Y",
            "PRODUCT_INFO": "설명2",
            "PRODUCT_COUNT": 570,
            "PAYMENT_COUNT": 87
        },
        {
            "PRODUCT_NO": 4,
            "CATEGORY_NO": 2,
            "PRODUCT_NAME": "제품4",
            "PRODUCT_PRICE": 11800,
            "PRODUCT_AMOUNT": 2,
            "PRODUCT_STATUS": "Y",
            "PRODUCT_INFO": "설명4",
            "PRODUCT_COUNT": 12,
            "PAYMENT_COUNT": 9
        },
        {
            "PRODUCT_NO": 11,
            "CATEGORY_NO": 2,
            "PRODUCT_NAME": "제품11",
            "PRODUCT_PRICE": 8700,
            "PRODUCT_AMOUNT": 2,
            "PRODUCT_STATUS": "Y",
            "PRODUCT_INFO": "설명11",
            "PRODUCT_COUNT": 60,
            "PAYMENT_COUNT": 48
        },
        {
            "PRODUCT_NO": 7,
            "CATEGORY_NO": 1,
            "PRODUCT_NAME": "제품7",
            "PRODUCT_PRICE": 26800,
            "PRODUCT_AMOUNT": 2,
            "PRODUCT_STATUS": "Y",
            "PRODUCT_INFO": "설명7",
            "PRODUCT_COUNT": 42,
            "PAYMENT_COUNT": 16
        },
        {
            "PRODUCT_NO": 6,
            "CATEGORY_NO": 2,
            "PRODUCT_NAME": "제품6",
            "PRODUCT_PRICE": 11800,
            "PRODUCT_AMOUNT": 2,
            "PRODUCT_STATUS": "Y",
            "PRODUCT_INFO": "설명6",
            "PRODUCT_COUNT": 20,
            "PAYMENT_COUNT": 18
        },
        {
            "PRODUCT_NO": 5,
            "CATEGORY_NO": 2,
            "PRODUCT_NAME": "제품5",
            "PRODUCT_PRICE": 11800,
            "PRODUCT_AMOUNT": 2,
            "PRODUCT_STATUS": "Y",
            "PRODUCT_INFO": "설명5",
            "PRODUCT_COUNT": 19,
            "PAYMENT_COUNT": 9
        }
    ]); 
    }

    getProduct(props.itemKey);
  }, []);

  return(
    <ListWrap>
      {productList.map((item) => {
        return <Product key={item.PRODUCT_NO} prdName={item.PRODUCT_NAME} prdPrice={item.PRODUCT_PRICE} imgPath={"/images/product/product01.jpg"}/>
      })}
    </ListWrap>
  )
}

export default ProductList;