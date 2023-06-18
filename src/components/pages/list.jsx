import { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import styled from 'styled-components';
import ProductList from '../template/productList';
import ContentsHead from '../template/contentsHead';

const ListWrap = styled.div`
  max-width: 1144px;
  margin: 0 15%;
`;
const FilterWrap = styled.div`
  display: flex;
  justify-content: space-between;
`;

const List = () => {
  const [ contents, setContents] = useState({});
  const { state } = useLocation();
  const { cate } = state;

  useEffect(() => {
    switch(cate) {
      case "best": 
        setContents({
          title: "BEST PICK!",
          content: "술술의 인기 상품들을 만나보세요!"
        });
        break;
      case "new":
        setContents({
          title: "NEW!",
          content: "술술의 새로운 상품들을 만나보세요!"
        });
        break;
      default:
        break;
    }
  }, [cate]);

  return(
    <ListWrap>
      <ContentsHead title={contents.title} content={contents.content}/>
      <div>
        <FilterWrap>
          <div>15 건의 결과가 있어요</div>
          <select>
            <option>추천순</option>
            <option>평점순</option>
            <option>판매순</option>
            <option>리뷰 많은순</option>
            <option>낮은 가격순</option>
            <option>높은 가격순</option>
          </select>
        </FilterWrap>
        <ProductList itemKey={cate}/>
      </div>
    </ListWrap>
  )
};

export default List;