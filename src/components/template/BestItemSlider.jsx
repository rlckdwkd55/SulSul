import React, { useState, useEffect } from "react";
import Slider from "react-slick";
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import Product from "../atoms/product";
import styled from "styled-components";
import ProductService from "../../service/ProductService";

const StyledSlider = styled(Slider)`
// .slider .slick-list {
//   margin:0 -20px;
// }

// .slick-slide {
//   margin:0 20px;
// }
`;

const BestItemSlider = () => {
  const [productList, setProductList] = useState([]);

  useEffect(()=>{
    async function getProduct() {
      const response = await ProductService.getMainItemList();

      if (response.status === "success") {
        setProductList(response.data["bestItems"]);
        console.log(response.data);
      }
    //   setProductList([
    //     {
    //       "PRODUCT_NO": 25,
    //       "CATEGORY_NO": 2,
    //       "PRODUCT_NAME": "제품25",
    //       "PRODUCT_PRICE": 25900,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명25",
    //       "PRODUCT_COUNT": 142,
    //       "PAYMENT_COUNT": 140
    //   },
    //   {
    //       "PRODUCT_NO": 2,
    //       "CATEGORY_NO": 2,
    //       "PRODUCT_NAME": "제품2",
    //       "PRODUCT_PRICE": 11000,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명2",
    //       "PRODUCT_COUNT": 570,
    //       "PAYMENT_COUNT": 87
    //   },
    //   {
    //       "PRODUCT_NO": 12,
    //       "CATEGORY_NO": 2,
    //       "PRODUCT_NAME": "제품12",
    //       "PRODUCT_PRICE": 37000,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명12",
    //       "PRODUCT_COUNT": 92,
    //       "PAYMENT_COUNT": 82
    //   },
    //   {
    //       "PRODUCT_NO": 30,
    //       "CATEGORY_NO": 2,
    //       "PRODUCT_NAME": "제품30",
    //       "PRODUCT_PRICE": 8700,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명30",
    //       "PRODUCT_COUNT": 89,
    //       "PAYMENT_COUNT": 80
    //   },
    //   {
    //       "PRODUCT_NO": 29,
    //       "CATEGORY_NO": 2,
    //       "PRODUCT_NAME": "제품29",
    //       "PRODUCT_PRICE": 9800,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명29",
    //       "PRODUCT_COUNT": 72,
    //       "PAYMENT_COUNT": 70
    //   },
    //   {
    //       "PRODUCT_NO": 3,
    //       "CATEGORY_NO": 2,
    //       "PRODUCT_NAME": "제품3",
    //       "PRODUCT_PRICE": 11000,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명3",
    //       "PRODUCT_COUNT": 842,
    //       "PAYMENT_COUNT": 49
    //   },
    //   {
    //       "PRODUCT_NO": 11,
    //       "CATEGORY_NO": 2,
    //       "PRODUCT_NAME": "제품11",
    //       "PRODUCT_PRICE": 8700,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명11",
    //       "PRODUCT_COUNT": 60,
    //       "PAYMENT_COUNT": 48
    //   },
    //   {
    //       "PRODUCT_NO": 28,
    //       "CATEGORY_NO": 1,
    //       "PRODUCT_NAME": "제품28",
    //       "PRODUCT_PRICE": 41900,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명28",
    //       "PRODUCT_COUNT": 46,
    //       "PAYMENT_COUNT": 46
    //   },
    //   {
    //       "PRODUCT_NO": 27,
    //       "CATEGORY_NO": 1,
    //       "PRODUCT_NAME": "제품27",
    //       "PRODUCT_PRICE": 32900,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명27",
    //       "PRODUCT_COUNT": 52,
    //       "PAYMENT_COUNT": 45
    //   },
    //   {
    //       "PRODUCT_NO": 24,
    //       "CATEGORY_NO": 2,
    //       "PRODUCT_NAME": "제품24",
    //       "PRODUCT_PRICE": 12900,
    //       "PRODUCT_AMOUNT": 2,
    //       "PRODUCT_STATUS": "Y",
    //       "PRODUCT_INFO": "설명24",
    //       "PRODUCT_COUNT": 39,
    //       "PAYMENT_COUNT": 29
    //   }
    // ]); 
    }

    getProduct();
  }, []);
  
  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 4
  };
  
  return (
    <div>
      <StyledSlider {...settings}>
        {productList.map((item, i) => {
          return <Product key={item.PRODUCT_NO} prdName={item.PRODUCT_NAME} prdPrice={item.PRODUCT_PRICE} imgPath={"/images/product/product01.jpg"}/>
        })}
      </StyledSlider>
    </div>
  )
}

export default BestItemSlider