import "./App.css";
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/layout/header";
import Footer from './components/layout/footer';
import Login from "./components/pages/login";
import Main from "./components/pages/main";
import Join from "./components/pages/join";
import List from "./components/pages/list";
import Search from "./components/pages/search";
import UserMy from "./components/pages/userMy";
import Detail from "./components/pages/detail";
import Cart from "./components/pages/cart";
import Order from "./components/pages/Order";
import GlobalStyle from "./Util/GlobalStyle";
import SNSJoin from "./components/pages/SNSJoin";
import Policy from './components/pages/Policy';
import Privacy from './components/pages/Privacy';
import styled from "styled-components";

const AppWrapper = styled.div`
  width: 100%;
  align-items: center;
  padding: 0 70px;
  min-width: 1300px;
  flex: 1;
`;
const PagesWrapper = styled.div`
  width: 100%;
  max-width: 1920px;
  min-width: 1300px;
`;
function App() {
  return (
    <React.Fragment>
      <GlobalStyle />
      <BrowserRouter>
        <Header />
        <AppWrapper>
          <PagesWrapper>
            <Routes>
              <Route path="/" element={<Main />} />
              <Route path="/login" element={<Login />} />
              <Route path="/join" element={<Join />} />
              <Route path="/list" element={<List />} />
              <Route path="/search" element={<Search />} />
              <Route path="/my" element={<UserMy />} />
              <Route path="/detail" element={<Detail />} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/order" element={<Order />} />
              <Route path="/SNSJoin" element={<SNSJoin />} />
              <Route path="/terms/policy" element={<Policy />} />
              <Route path="/terms/privacy" element={<Privacy />} />
            </Routes>
          </PagesWrapper>
        </AppWrapper>
        <Footer/>
      </BrowserRouter>
    </React.Fragment>
  );
}

export default App;
