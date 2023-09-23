import { Link } from "react-router-dom";
import SearchBar from "./searchBar";
import axios from "axios";
import MenuList from "../template/menuList";
import styled from 'styled-components';

const Wrap =styled.div`
  min-width: 1300px;
`;

function LoginOut() {
  const isLogin = sessionStorage.getItem("isLogin");
  let contents;
  if (isLogin !== "true") {
    contents = (
      <Link to="/login">
          {/* <img src='/images/login_btnG.png' alt='네이버로그인'/> */}
          <span>로그인</span>
      </Link>
    );
  } else {
    contents = (
      <>
        <span
          className="btn"
          id="logout-btn"
          onClick={() => {
            sessionStorage.setItem('isLogin', false);
            sessionStorage.setItem('userEmail', '');
            axios.post("/logout");
          }}
        >
          로그아웃
        </span>
        <Link to="/my">
          <div className="btn" id="move-mypage-btn">
            My Page
          </div>
        </Link>
        <Link to="/cart">
          <div className="btn" id="move-cart-btn">
            <i className="icon bi bi-bag"></i>
          </div>
        </Link>
      </>
    );
  }
  return contents;
}

function Header() {
  return (
    <Wrap>
      <header>
        <div className="header">
          <div className="header-left">
            <div className=" btn logo">
              <Link to="/">
                <img
                  src="/images/logo/sulsul_logo2.png"
                  alt="sulsul logo"
                  width="90"
                  height="70"
                />
              </Link>
            </div>
            <SearchBar />
          </div>
          <div className="header-right">
            <LoginOut />
          </div>
        </div>
      </header>
      <MenuList />
    </Wrap>
  );
}

export default Header;
