import { Link } from "react-router-dom";
import SearchBar from "./searchBar";
import axios from "axios";

function LoginOut() {
  const isLogin = sessionStorage.getItem("isLogin");
  let contents;

  if (isLogin !== "true") {
    contents = (
      <Link to="/login">
        <span className="btn" id="move-login-btn">
          회원가입 / 로그인
        </span>
      </Link>
    );
  } else {
    contents = (
      <>
        <span
          className="btn"
          id="logout-btn"
          onClick={() => {
            axios.post("/logout");
          }}
        >
          로그아웃
        </span>
        <Link to="/my">
          <div className="btn" id="move-mypage-btn">
            <i className="icon bi bi-bag"></i>
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
    <>
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
      <nav className="nav">
        <div className="menu-list-warpper">
          <ul className="menu-list">
            <li>
              <a href="/">소주 · 증류주</a>
            </li>
            <li>
              <a href="/">막걸리</a>
            </li>
            <li>
              <a href="/">약주 · 청주</a>
            </li>
            <li>
              <a href="/">과실주</a>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
}

export default Header;
