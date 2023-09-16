import styled from "styled-components";

const ContentsHeadWrap = styled.div`
  background-color: rgb(255 240 240);
  border-radius: 10px;
  display: flex;
  padding: 30px;
  margin-bottom: 35px;
`;
const Title = styled.h2`
  margin-bottom: 15px;
`;
const Content = styled.p`

`;

const ContentsHead = (props) => {
  return (
    <ContentsHeadWrap>
      <div>
        <Title>{props.title}</Title>
        <Content>{props.content}</Content>
      </div>
      <div>

      </div>
    </ContentsHeadWrap>
  )
};

export default ContentsHead;