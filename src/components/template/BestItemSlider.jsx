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
    color: ${rootColor.color};
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
    speed: 800,
    slidesToShow: 4,
    slidesToScroll: 4,
    autoplay: true,
    autoplaySpeed: 5000
  };
  
  return (
    <div>
      <StyledSlider {...settings}>
        { productList && productList.length > 0 ? (
            productList.map((item, i) => {
            return <Best_Product key={item.productNo} prdNo={item.productNo} prdName={item.productName} prdPrice={item.productPrice} imgPath={"/images/product/cate0" + item.categoryNo + "/0" + item.categoryNo + item.productNo + "_Image.jpg"}/>
            })
          ) : (
            <div style={{margin: '10px 0 0 20px'}}>등록 된 상품이 없습니다.</div>
          )
        }
      </StyledSlider>
    </div>
  )
}

export default BestItemSlider