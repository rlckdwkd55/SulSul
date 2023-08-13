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
      if (/^newItems$|^bestItems$/.test(itemKey)) {
        const response = await ProductService.getMainItemList();
  
        if (response.status === "success") {
          setProductList(response.data["newItems"]);
          console.log(response.data);
        }
      } else {
        const data = {
          'cate': itemKey
        }

        const response = await ProductService.postCategoryList(data);
  
        if (response.status === "success") {
          setProductList(response.data[itemKey]);
          console.log(response.data);
        }
      }
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