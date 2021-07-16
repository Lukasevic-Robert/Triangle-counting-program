import React, { useContext} from 'react';
import TriangleCalculatorForm from './TriangleCalculatorForm';
import Typography from '@material-ui/core/Typography';
import { CalculatorContext } from '../context/CalculatorContext';


export default function TriangleCalculator() {

  const { response } = useContext(CalculatorContext);

    return (

     <div> 
       <Typography style={{fontFamily: 'Tourney', color: '#4b42f5'}} variant="h1">Triangle Calculator</Typography>
       <br/>
       <Typography style={{fontFamily: 'Tourney', color: '#4b42f5'}} variant="h4">Triangles Count: <span style={{fontFamily: 'Tourney', color: 'orange', fontSize: '50px'}}>{response}</span></Typography>
       <Typography style={{fontFamily: 'Encode Sans SC'}} variant="h5">Enter X Y coordinates</Typography>
       <TriangleCalculatorForm/>
     </div>
    )
}
