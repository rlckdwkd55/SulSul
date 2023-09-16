import React, { useState, useEffect } from "react";
import Slider from "react-slick";
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import Best_Product from "../atoms/best_product";
import styled from "styled-components";
import ProductService from "../../service/ProductService";
import { rootColor } from "../../Util/GlobalStyle";

const StyledSlider = styled(Slider)`
  .slick-list {
    margin-left: -20px;
  }
  .slick-prev:before, .slick-next:before {
    color: ${rootColor.color},
    font-size: 30px;
  }
  .slick-prev {
    left: -55px;
  }
  .slick-next {
    right: -55px;
  }
`;

const BestItemSlider = () => {
  const [productList, setProductList] = useState([]);

  useEffect(()=>{
    async function getProduct() {
      const response = await ProductService.getMainItemList();

      if (response.status === "success") {
        setProductList(response.data["bestItems"]);
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
          return <Best_Product key={item.PRODUCT_NO} prdNo={item.PRODUCT_NO} prdName={item.PRODUCT_NAME} prdPrice={item.PRODUCT_PRICE} imgPath={"/images/product/cate0" + item.CATEGORY_NO + "/0" + item.CATEGORY_NO + item.PRODUCT_NO + "_Image.jpg"}/>
        })}
      </StyledSlider>
    </div>
  )
}

export default BestItemSlider