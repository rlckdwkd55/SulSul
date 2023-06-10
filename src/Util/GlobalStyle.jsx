import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
  *, *::before, *::after {
    box-sizing: border-box;
  }
  body  {
  margin: 0;
  box-sizing: border-box;
  font-family:  -apple-system, BlinkMacSystemFont,
    "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans",
    "Droid Sans", "Helvetica Neue", sans-serif;
}
  h1 {
    font-size: 1rem;
    margin: 0;
  }
  h2 {
    font-size: 1rem;
    font-weight: 400;
    margin: 0;
  }
  h3 {
    font-size: 1rem;
    font-weight: 400;
    margin: 0;
  }
  h4 {
    font-size: 1rem;
    font-weight: 400;
    margin: 0;
  }
  h5 {
    font-size: 1rem;
    font-weight: 400;
    margin: 0;
  }
  h6 {
    font-size: 1rem;
    font-weight: 400;
    margin: 0;
  }
  p {
    font-size: 1rem;
    margin: 0;
  }

`;

export default GlobalStyle;
