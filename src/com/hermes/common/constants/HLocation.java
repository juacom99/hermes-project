/*
Hermes Project - Chat client/server for Ares Galaxy P2P
Copyright (C) 2011  Joaquin Martinez (juacom04@gmail.com)

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/
package com.hermes.common.constants;

import java.util.EnumSet;
import java.util.HashMap;
    /*
    * This Enumerate represents the posibles admins levels in the Ares Galaxy Protocol
    * 0-UnKnown
    * 1-Afghanistan
    * 2-Albania
    * 3-Algeria
    * 4-Andorra
    * 5-Angola
    * 6-Anguilla
    * 7-Antarctia
    8-Antigua and Barbuda
    9-Argentina
    10-Armenia
    11-Aruba
    12-Australia
    13-Austria
    14-Azerbaijan
    15-Bahamas
    16-Bahrain
    17-Bangladesh
    18-Barbados
    19-Belarus
    20-Belgium
    21-Belize
    22-Berin
    23-Bermuda
    24-Bhutan
    25-Bolivia
    26-Bosnia and Herzegovina
    27-Botswana
    28-Brazil
    29-Brunei
    30-Bulgaria
    31-Burkina Faso
    32-Burundi
    33-Cambodia
    34-Cameroon
    35-Canada
    36-Cape Verde
    37-Cayman Islands
    38-Central African Republic
    39-Chad
    40-Chile
    41-China
    42-Christmas Islands
    43-Cocos Islands
    44-Colombia
    45-Comoros
    46-Congo
    47-Congo
    48-Cook Islands
    49-Costa Rica
    50-Croatia
    51-Cuba
    52-Cyprus
    53-Czech Republic
    54-Denmark
    55-Fjibouti
    56-Dominica
    57-Dominican Republic
    58-Dutch antilles
    59-East Timor
    60-Ecuador
    61-Egypt
    62-El Salvador
    63-Equatorial Guinea
    64-Entea
    65-Estonia
    66-Ethiopia
    67-Falkland Islands
    68-Faroe Islands
    69-Fiji Islands
    70-Finland
    71-France
    72-French Polynesia
    73-Gabon
    74-Gambia
    75-Gaza
    76-Georgia
    77-Germany
    78-Ghana
    79-Gibraltar
    80-Greece
    81-Greenland
    82-Grenada
    83-Geuadaloupe
    84-Guatemala
    85-Guernsey
    86-Guinea
    87-Guinea-Bissau
    88-Guyana
    89-Guyana
    90-Haiti
    91-Honduras
    92-Hong Kong
    93-Hungary
    94-Iceland
    95-India
    96-Indonesia
    97-Iran
    98-Iraq
    99-Ireland
    100-Isle of Man
    101-Israel
    102-Italy
    103-Ivory Coast
    104-Jamaica
    105-Japan
    106-Jersey
    107-Jordan
    108-Kazakhstan
    109-Kenya
    110-Kiribati
    111-Kuwwait
    112-Kyrgyzstan
    113-Laos
    114-Latvia
    115-Lebanon
    116-Lesotho
    117-Liberia
    118-Libya
    119-Liechtenstein
    120-Lithuania
    121-Luxembourg
    122-Macao
    123-Macedonia
    124-Madagascar
    125-Malawi
    126-Malaysia
    127-Maldives
    128-Mali
    129-Malta
    130-Marshall Islands
    131-Martinique
    132-Mauritania
    133-Mauritius
    134-Mayotte
    135-Mexico
    136-Micronesia
    137-Moldova
    138-Monaco
    139-Mongolia
    140-Montserrat
    141-Morocco
    142-Mozambique
    143-Myanmar
    144-Namibia
    145-Nauru
    146-Nepal
    147-Netherlands
    148-New Caledonia
    149-New Zealand
    150-Nicaragua
    151-Niger
    152-Nigeria
    153-Niue
    154-Norfolk Island
    155-North Korea
    156-Norway
    157-Oman
    158-Pakistan
    159-Palau
    160-Panama
    161-Papua New Guinea
    162-Paraguay
    163-Peru
    164-Phillippines
    165-Pitcairn Island
    166-Poland
    167-Portugal
    168-Puerto Rico
    169-Qatar
    170-Reunion
    171-Romania
    172-Russia
    173-Rwanda
    174-Samoa
    175-San Marino
    176-Sao Tome and Principe
    177-Saudi Arabia
    178-Senegal
    179-Seychelles
    180-Sierra Leone
    181-Singapore
    182-Slovakia
    183-Slovenia
    184-Solomon Island
    185-Somalia
    186-South Africa
    187-South Georgia Island
    188-South Korea
    189-Spain
    190-Sri Lanka
    191-St Helens
    192-St Kitts and Nevis
    193-St Lucia
    194-St Pierre and Miquelon
    195-St Vicent
    196-Sudan
    197-Suriname
    198-Svalbard
    199-Swaziland
    200-Sweden
    201-Switzerland
    202-Syria
    203-Taiwan
    204-Tajikistan
    205-Tanzania
    206-Thailand
    207-Togo
    208-Tokelau
    209-Tonga
    210-Trinidad and Tobago
    211-Tunisia
    212-Turkey
    213-Turkmenistan
    214-Turks and Caicos Islands
    215-Tuvalu
    216-Uganda
    217-Ukraine
    218-United Arab Emirates
    219-United Kingdom
    220-United States
    221-Uruguay
    222-Uzbekistan
    223-Vanuatu
    224-Venezuela
    225-Vietnam
    226-Virgin Islands
    227-Wallis and Futuna
    228-West Bank
    229-Western Sahara
    230-Yemen
    231-Yugoslavia
    232-Zambia
    233-Zimbabwe
    */
public enum HLocation
{

    UnKnown((byte)0),
    Afghanistan((byte) 1),
    Albania((byte) 2),
    Algeria((byte) 3),
    Andorra((byte) 4),
    Angola((byte) 5),
    Anguilla((byte) 6),
    Antarctia((byte) 7),
    Antigua_and_Barbuda((byte) 8),
    Argentina((byte) 9),
    Armenia((byte) 10),
    Aruba((byte) 11),
    Australia((byte) 12),
    Austria((byte) 13),
    Azerbaijan((byte) 14),
    Bahamas((byte) 15),
    Bahrain((byte) 16),
    Bangladesh((byte) 17),
    Barbados((byte) 18),
    Belarus((byte) 19),
    Belgium((byte) 20),
    Belize((byte) 21),
    Berin((byte) 22),
    Bermuda((byte) 23),
    Bhutan((byte) 24),
    Bolivia((byte) 25),
    Bosnia_and_Herzegovina((byte) 26),
    Botswana((byte) 27),
    Brazil((byte) 28),
    Brunei((byte) 29),
    Bulgaria((byte) 30),
    Burkina_Faso((byte) 31),
    Burundi((byte) 32),
    Cambodia((byte) 33),
    Cameroon((byte) 34),
    Canada((byte) 35),
    Cape_Verde((byte) 36),
    Cayman_Islands((byte) 37),
    Central_African_Republic((byte) 38),
    Chad((byte) 39),
    Chile((byte) 40),
    China((byte) 41),
    Christmas_Islands((byte) 42),
    Cocos_Islands((byte) 43),
    Colombia((byte) 44),
    Comoros((byte) 45),
    Congo((byte) 46),
//  Congo((byte) 47),
    Cook_Islands((byte) 48),
    Costa_Rica((byte) 49),
    Croatia((byte) 50),
    Cuba((byte) 51),
    Cyprus((byte) 52),
    Czech_Republic((byte) 53),
    Denmark((byte) 54),
    Fjibouti((byte) 55),
    Dominica((byte) 56),
    Dominican_Republic((byte) 57),
    Dutch_antilles((byte) 58),
    East_Timor((byte) 59),
    Ecuador((byte) 60),
    Egypt((byte) 61),
    El_Salvador((byte) 62),
    Equatorial_Guinea((byte) 63),
    Entea((byte) 64),
    Estonia((byte) 65),
    Ethiopia((byte) 66),
    Falkland_Islands((byte) 67),
    Faroe_Islands((byte) 68),
    Fiji_Islands((byte) 69),
    Finland((byte) 70),
    France((byte) 71),
    French_Polynesia((byte) 72),
    Gabon((byte) 73),
    Gambia((byte) 74),
    Gaza((byte) 75),
    Georgia((byte) 76),
    Germany((byte) 77),
    Ghana((byte) 78),
    Gibraltar((byte) 79),
    Greece((byte) 80),
    Greenland((byte) 81),
    Grenada((byte) 82),
    Geuadaloupe((byte) 83),
    Guatemala((byte) 84),
    Guernsey((byte) 85),
    Guinea((byte) 86),
//    Guinea((byte) 87),
    Guyana((byte) 88),
 //   Guyana((byte) 89),
    Haiti((byte) 90),
    Honduras((byte) 91),
    Hong_Kong((byte) 92),
    Hungary((byte) 93),
    Iceland((byte) 94),
    India((byte) 95),
    Indonesia((byte) 96),
    Iran((byte) 97),
    Iraq((byte) 98),
    Ireland((byte) 99),
    Isle_of_Man((byte) 100),
    Israel((byte) 101),
    Italy((byte) 102),
    Ivory_Coast((byte) 103),
    Jamaica((byte) 104),
    Japan((byte) 105),
    Jersey((byte) 106),
    Jordan((byte) 107),
    Kazakhstan((byte) 108),
    Kenya((byte) 109),
    Kiribati((byte) 110),
    Kuwwait((byte) 111),
    Kyrgyzstan((byte) 112),
    Laos((byte) 113),
    Latvia((byte) 114),
    Lebanon((byte) 115),
    Lesotho((byte) 116),
    Liberia((byte) 117),
    Libya((byte) 118),
    Liechtenstein((byte) 119),
    Lithuania((byte) 120),
    Luxembourg((byte) 121),
    Macao((byte) 122),
    Macedonia((byte) 123),
    Madagascar((byte) 124),
    Malawi((byte) 125),
    Malaysia((byte) 126),
    Maldives((byte) 127),
    Mali((byte) 128),
    Malta((byte) 129),
    Marshall_Islands((byte) 130),
    Martinique((byte) 131),
    Mauritania((byte) 132),
    Mauritius((byte) 133),
    Mayotte((byte) 134),
    Mexico((byte) 135),
    Micronesia((byte) 136),
    Moldova((byte) 137),
    Monaco((byte) 138),
    Mongolia((byte) 139),
    Montserrat((byte) 140),
    Morocco((byte) 141),
    Mozambique((byte) 142),
    Myanmar((byte) 143),
    Namibia((byte) 144),
    Nauru((byte) 145),
    Nepal((byte) 146),
    Netherlands((byte) 147),
    New_Caledonia((byte) 148),
    New_Zealand((byte) 149),
    Nicaragua((byte) 150),
    Niger((byte) 151),
    Nigeria((byte) 152),
    Niue((byte) 153),
    Norfolk_Island((byte) 154),
    North_Korea((byte) 155),
    Norway((byte) 156),
    Oman((byte) 157),
    Pakistan((byte) 158),
    Palau((byte) 159),
    Panama((byte) 160),
    Papua_New_Guinea((byte) 161),
    Paraguay((byte) 162),
    Peru((byte) 163),
    Phillippines((byte) 164),
    Pitcairn_Island((byte) 165),
    Poland((byte) 166),
    Portugal((byte) 167),
    Puerto_Rico((byte) 168),
    Qatar((byte) 169),
    Reunion((byte) 170),
    Romania((byte) 171),
    Russia((byte) 172),
    Rwanda((byte) 173),
    Samoa((byte) 174),
    San_Marino((byte) 175),
    Sao_Tome_and_Principe((byte) 176),
    Saudi_Arabia((byte) 177),
    Senegal((byte) 178),
    Seychelles((byte) 179),
    Sierra_Leone((byte) 180),
    Singapore((byte) 181),
    Slovakia((byte) 182),
    Slovenia((byte) 183),
    Solomon_Island((byte) 184),
    Somalia((byte) 185),
    South_Africa((byte) 186),
    South_Georgia_Island((byte) 187),
    South_Korea((byte) 188),
    Spain((byte) 189),
    Sri_Lanka((byte) 190),
    St_Helens((byte) 191),
    St_Kitts_and_Nevis((byte) 192),
    St_Lucia((byte) 193),
    St_Pierre_and_Miquelon((byte) 194),
    St_Vicent((byte) 195),
    Sudan((byte) 196),
    Suriname((byte) 197),
    Svalbard((byte) 198),
    Swaziland((byte) 199),
    Sweden((byte) 200),
    Switzerland((byte) 201),
    Syria((byte) 202),
    Taiwan((byte) 203),
    Tajikistan((byte) 204),
    Tanzania((byte) 205),
    Thailand((byte) 206),
    Togo((byte) 207),
    Tokelau((byte) 208),
    Tonga((byte) 209),
    Trinidad_and_Tobago((byte) 210),
    Tunisia((byte) 211),
    Turkey((byte) 212),
    Turkmenistan((byte) 213),
    Turks_and_Caicos_Islands((byte) 214),
    Tuvalu((byte) 215),
    Uganda((byte) 216),
    Ukraine((byte) 217),
    United_Arab_Emirates((byte) 218),
    United_Kingdom((byte) 219),
    United_States((byte) 220),
    Uruguay((byte) 221),
    Uzbekistan((byte) 222),
    Vanuatu((byte) 223),
    Venezuela((byte) 224),
    Vietnam((byte) 225),
    Virgin_Islands((byte) 226),
    Wallis_and_Futuna((byte) 227),
    West_Bank((byte) 228),
    Western_Sahara((byte) 229),
    Yemen((byte) 230),
    Yugoslavia((byte) 231),
    Zambia((byte) 232),
    Zimbabwe((byte) 233);
   
    private byte value;
    private static final HashMap<Byte,HLocation> lookup = new HashMap<Byte,HLocation>();

     static {
          for(HLocation l : EnumSet.allOf(HLocation.class))
               lookup.put(l.getValue(), l);
     }

    private HLocation(byte value)
    {
        this.value = value;
    }

    public byte getValue()
    {
        return value;
    }

    public static HLocation get(byte code)
    {
          return lookup.get(code);
     }

    @Override
    public String toString()
    {
        return super.toString().replace("_"," ");
    }

    
    
}