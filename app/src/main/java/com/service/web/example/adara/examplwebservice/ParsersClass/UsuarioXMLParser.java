package com.service.web.example.adara.examplwebservice.ParsersClass;

import com.service.web.example.adara.examplwebservice.POJO.Usuario;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adara on 10/30/2016.
 */
public class UsuarioXMLParser {

    public static List<Usuario> parser(String data){
        boolean intDataItemTag = false;
        String currentTagName = "";
        Usuario usuario = null;
        List <Usuario> usuarioList = new ArrayList<>();

        try {
            //Inicializamos
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(data));

            //Obtengo el tipo de evento y lo almaceno en eventType
            int eventType = parser.getEventType();

            //Mientras no llegue al fin del documento ejecutar lo que se ecuentra dentro de el
            while (eventType != XmlPullParser.END_DOCUMENT ){
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName(); //Obtenemos el nombre del tag
                        if(currentTagName.equals("usuario")){
                            intDataItemTag = true;
                            usuario = new Usuario();
                            usuarioList.add(usuario);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("usuario")){
                            intDataItemTag = false;
                        }
                        currentTagName = "";
                        break;
                    case XmlPullParser.TEXT:
                        if(intDataItemTag && usuario != null){
                            //Creamos otro swich porque texto tendra diferentes casos
                            //Seteamos los datos de cada nodo
                            switch (currentTagName){
                                case "usuarioId":
                                    usuario.setUsuarioId(Integer.parseInt(parser.getText()));
                                    break;
                                case "nombre":
                                    usuario.setNombre(parser.getText());
                                    break;
                                case "email":
                                    usuario.setEmail(parser.getText());
                                    break;
                            }
                        }
                        break;
                }
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return usuarioList;
    }
}
