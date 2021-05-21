import React from 'react'
import Head from 'next/head'

import { Container } from '../styles/pages/Home'

const Home: React.FC = () => {
  return (
    <Container>
      <Head>
        <title>JsonGenerate</title>
      </Head>

      <h1>Api Json Generator</h1>
      <p>Formate seus Jsons</p>
    </Container>
  )
}

export default Home
