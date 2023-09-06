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
          return <Product key={item.PRODUCT_NO} prdNo={item.PRODUCT_NO} prdName={item.PRODUCT_NAME} prdPrice={item.PRODUCT_PRICE} imgPath={"/images/product/product01.jpg"}/>
        })}
      </StyledSlider>
    </div>
  )
}

export default BestItemSlider