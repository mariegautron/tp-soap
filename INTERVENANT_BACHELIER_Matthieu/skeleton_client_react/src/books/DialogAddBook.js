import React, { useState } from 'react'
import soapRequest from 'easy-soap-request'
import XMLParser from 'react-xml-parser'
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@material-ui/core'

import { endpointBook, defaultHeaders } from '../config'

export default function DialogAddBook({ open, handleClose, reload }) {
  const [title, setTitle] = useState('')
  const [isbn, setIsbn] = useState('')
  const [year, setYear] = useState('')
  const [authorId, setAuthorId] = useState('')

  const addBook = async () => {
    if (title === '' || isbn === '' || year === '' || authorId === '') {
      return
    }
    let xml = `
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ynov="http://nantes.ynov.com/soap/book">
	    <soapenv:Header />
	    <soapenv:Body>
        
        TODO

	    </soapenv:Body>
    </soapenv:Envelope>`

    const { response } = await soapRequest({ url: endpointBook, headers: defaultHeaders, xml })
    const { body } = response
    let xmlParser = new XMLParser().parseFromString(body)
    console.log(xmlParser)
    reload()
  }

  return (
    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
      <DialogTitle id="form-dialog-title">Ajouter un nouveau livre</DialogTitle>
      <DialogContent>
        <DialogContentText>Pour ajouter un livre, veuillez saisir les champs demandés</DialogContentText>
        <TextField autoFocus margin="dense" id="title" label="Titre" fullWidth onChange={(e) => setTitle(e.target.value)} required />
        <TextField margin="dense" id="isbn" label="ISBN" fullWidth onChange={(e) => setIsbn(e.target.value)} required />
        <TextField
          margin="dense"
          id="year"
          label="Date de publication"
          type="number"
          fullWidth
          onChange={(e) => setYear(e.target.value)}
          required
        />
        <TextField
          margin="dense"
          id="authorId"
          label="Author ID"
          type="number"
          fullWidth
          onChange={(e) => setAuthorId(e.target.value)}
          required
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Annuler
        </Button>
        <Button variant="outlined" onClick={addBook} color="primary">
          Ajouter
        </Button>
      </DialogActions>
    </Dialog>
  )
}
