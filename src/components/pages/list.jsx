import { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import styled from 'styled-components';
import ProductList from '../template/productList';
import ContentsHead from '../template/contentsHead';

const ListWrap = styled.div`
  max-width: 1144px;
  margin: auto;
`;
const FilterWrap = styled.div`
  display: flex;
  justify-content: space-between;
`;

const List = () => {
  const [ contents, setContents] = useState({});
  const [ prdCnt, setPrdCnt ] = useState(0);
  const { state } = useLocation();
  const cate = state.cate;
  const word = state.word;

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
      case 1:
        setContents({
          title: "소주 · 증류주",
          content: "소주"
        });
        break;
      case 2:
        setContents({
          title: "막걸리",
          content: "막걸리"
        });
        break;
      case 3:
        setContents({
          title: "약주 · 청주",
          content: "약주"
        });
        break;
      case 4:
        setContents({
          title: "과실주",
          content: "과실주"
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
          <div>{prdCnt} 건의 결과가 있어요</div>
          <select>
            <option>추천순</option>
            <option>평점순</option>
            <option>판매순</option>
            <option>리뷰 많은순</option>
            <option>낮은 가격순</option>
            <option>높은 가격순</option>
          </select>
        </FilterWrap>
        <ProductList itemKey={cate} searchWord={word} setPrdCnt={setPrdCnt}/>
      </div>
    </ListWrap>
  )
};

export default List;