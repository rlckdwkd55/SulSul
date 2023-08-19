import { useState } from "react";
import { useNavigate } from "react-router-dom";
import ProductService from "../../service/ProductService";

function SearchList(props) {
  const lis = [];

  props.nameList.forEach((item, index) => {
    lis.push(
      <li
        key={index}
        onClick={(e) => {
          e.preventDefault();
          props.setSearch(item);
        }}
      >
        {item}
      </li>
    );
  });

  console.log(lis);

  return (
    <div className="reqList" style={{ display: "block" }}>
      <ul>{lis}</ul>
    </div>
  );
}

function SearchBar() {
  const [search, setSearch] = useState("");
  const [nameList, setNameList] = useState([]); // 연관 검색어
  const navigate = useNavigate();

  async function changeSearch(e) {
    let searchVal = e.target.value;
    setSearch(searchVal);

    const data = {
      requestString: searchVal
    };

    const response = await ProductService.postProductName(data);
    if (response.status === 'success') {
      setNameList(response.data)
    }
  }

  async function doSearch(e) {
    if (search.length === 0) e.preventDefault();
    navigate("/list", { state: { word: search } });
  }

  let contents;
  if (search) {
    contents = <SearchList nameList={nameList} setSearch={setSearch} />;
  } else contents = "";

  return (
    <div className="search-wrapper">
      <div className="search">
        <input type="text" id="search-input" name="searchBar" onChange={(e) => {changeSearch(e);}}
          onKeyDown={(e) => {
            if (e.key === "Enter") doSearch(e);
          }}
          value={search}
        />
        <div id="searchBtn" onClick={(e) => doSearch(e)}>
          <i className="icon fa-solid fa-magnifying-glass"></i>
        </div>
      </div>
      {contents}
    </div>
  );
}

export default SearchBar;
