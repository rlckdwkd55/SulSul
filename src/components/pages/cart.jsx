import { useState, useEffect } from "react";
import styled from "styled-components";
import CartList from "../template/cart/cartList";
import CartBill from "../template/cart/cartBill";
import CartService from "../../service/CartService";

const Wrap = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 50px 150px;
`;

const Cart = () => {
  const [ cartList, setCartList ] = useState([]);
  const [ price, setPrice] = useState(0);
  const [ selected, setSelected ] = useState([]);

  useEffect(()=>{
    async function getCartList() {
      // const response = await ProductService.postCartList();
  
      // if (response.status === "success") {
      //   setCartList(response.data);
      // }
    }

    setCartList([
      {
        "prdNo": 1,
        "prdName": '안동소주',
        "prdPrice": 10000
      },
      {
        "prdNo": 2,
        "prdName": '가평잣막걸리',
        "prdPrice": 20000
      }
    ]);

    getCartList();

  }, []);

  return(
    <Wrap>
      <CartList cartList={cartList} setPrice={setPrice} selected={selected} setSelected={setSelected}/>
      <CartBill price={price} selected={selected}/>
    </Wrap>
  )
}

export default Cart;