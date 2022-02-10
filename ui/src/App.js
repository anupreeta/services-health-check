import React, { useEffect, useState } from 'react';
import './App.css';
import { getList } from './components/list';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

function App() {
  const [list, setList] = useState([]);

  useEffect(() => {
    let mounted = true;
    getList()
      .then(items => {
        if(mounted) {
          setList(items)
        }
      })
    return () => mounted = false;
  }, [])

  return(
    <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>id</TableCell>
                <TableCell align="right">name</TableCell>
                <TableCell align="right">url</TableCell>
                <TableCell align="right">status</TableCell>
                <TableCell align="right">created</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {list.map((item) => (
                <TableRow
                  key={item.name}
                  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  <TableCell component="th" scope="row">
                    {item.id}
                  </TableCell>
                  <TableCell align="right">{item.name}</TableCell>
                  <TableCell align="right">{item.url}</TableCell>
                  <TableCell align="right">{item.status}</TableCell>
                  <TableCell align="right">{item.created}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
  )
}

export default App;