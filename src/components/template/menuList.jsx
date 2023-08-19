import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

const MenuWrap = styled.div`
  display: flex;
  padding-top: 15px;
  height: 55px;
  padding: 15px 70px 0 70px;
  border-top: 1px solid lightgrey;
  justify-content: center;

`;

const Ul = styled.ul`
  list-style: none;
  padding: 0;
`;
const Li = styled.li`
  float: left;
  margin: 0 20px;

  > div {
    font-size: 13pt;
    color: dimgrey;
    text-decoration-line: none; 
  }

  > div:hover {
    cursor: pointer;
  }
`;

const MenuList = () => {
  const [menuList, setMenuList] = useState([
    {
      title: "소주 · 증류주",
      cate: 1
    },
    {
      title: "막걸리",
      cate: 2
    },
    {
      title: "약주 · 청주",
      cate: 3
    },
    {
      title: "과실주",
      cate: 4
    }
  ]);
  const navigate = useNavigate();

  function navigatePage(cate){
    navigate('/list', {state: {cate: cate}});
  };

  return (
    <MenuWrap>
      <Ul>
        { menuList.map(item => {
          return <Li key={item.cate} cate={item.cate}><div onClick={() => navigatePage(item.cate)}>{item.title}</div></Li>
        }) }
      </Ul>
    </MenuWrap>
  );
};

export default MenuList;