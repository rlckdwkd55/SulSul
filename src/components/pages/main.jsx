import { useNavigate } from 'react-router-dom'; 
import styled from 'styled-components';
import ProductList from '../template/productList';
import BestItemSlider from '../template/BestItemSlider';
import { rootColor } from '../../Util/GlobalStyle';

const MainWrap = styled.div`
    max-width: 1144px;
    margin: auto;
`;
const BestItemsWrap = styled.div`
    margin: auto;
`;
const NewItemsWrap = styled.div`
    margin: auto;
    margin-top: 100px;
`;
const ContentHeadWrap = styled.div`
    > div:nth-child(1) {
        color: ${rootColor.color};
        font-weight: bold;
        font-size: 15pt;
    }
`;

const ContentHead = (props) => {
    const navigate = useNavigate();
    return (
        <ContentHeadWrap>
            <div>{props.title}</div>
            <div>{props.content}</div>
        </ContentHeadWrap>
    )
}

const Main = () => {

    return(
        <MainWrap>
            <div id="banner">

            </div>
            <BestItemsWrap>
                <ContentHead title={'BEST PICK!'} cate={'best'} content={'술술의 인기 상품들을 만나보세요!'}/>
                <BestItemSlider/>
            </BestItemsWrap>
            <NewItemsWrap>
                <ContentHead title={'NEW!'} cate={'new'} content={'술술의 새로운 상품들을 만나보세요!'} />
                <ProductList itemKey={'newItems'} />
            </NewItemsWrap>
        </MainWrap>
    );
}

export default Main