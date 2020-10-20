/*! html_codesniffer - v2.2.0 - 2018-08-21 */
/**
 * +--------------------------------------------------------------------+
 * | This HTML_CodeSniffer file is Copyright (c)                        |
 * | Squiz Pty Ltd (ABN 77 084 670 600)                                 |
 * +--------------------------------------------------------------------+
 * | IMPORTANT: Your use of this Software is subject to the terms of    |
 * | the Licence provided in the file licence.txt. If you cannot find   |
 * | this file please contact Squiz (www.squiz.com.au) so we may        |
 * | provide you a copy.                                                |
 * +--------------------------------------------------------------------+
 *
 */
(function(root, factory) {
    if (typeof define === 'function' && define.amd) {
        define(['htmlcs'], factory);
    } else if (typeof exports === 'object') {
        module.exports = factory();
    } else {
        var exports = factory();
        for (var prop in exports) {
            root[prop] = exports[prop];
        }
    }
}(this, function() {
    var _global = {
        translation: {}
    };

    _global.translation.en = {
        auditor_name: "HTML_CodeSniffer by Squiz",
        auditor_using_standard: "Using standard",
        auditor_standards: "Standards",
        auditor_code_snippet: "Code Snippet",
        auditor_close: "Close",
        auditor_select_types: "Select the types of issues to include in the report",
        auditor_home: "Home",
        auditor_view_report: "View Report",
        auditor_report: "Report",
        auditor_back_to_report: "Back to Report",
        auditor_previous_issue: "Previous Issue",
        auditor_next_issue: "Next Issue",
        auditor_issue: "Issue",
        auditor_of: "of",
        auditor_errors: "Errors",
        auditor_error: "Error",
        auditor_warnings: "Warnings",
        auditor_warning: "Warning",
        auditor_notices: "Notices",
        auditor_notice: "Notice",
        auditor_toggle_display_of: "Toggle display of",
        auditor_messages: "messages",
        auditor_unable_to_point: "Unable to point to the element associated with this issue.",
        auditor_unable_to_point_entire: "Unable to point to this issue, as it relates to the entire document.",
        auditor_unable_to_point_removed: "Unable to point to this element as it has been removed from the document since the report was generated.",
        auditor_unable_to_point_outside: "Unable to point to this element because it is located outside the document's body element.",
        auditor_unable_to_point_hidden: "Unable to point to this element because it is hidden from view, or does not have a visual representation.",
        auditor_point_to_element: "Point to Element",
        auditor_unsupported_browser: "The code snippet functionality is not supported in this browser.",
        auditor_page: "Page",
        auditor_updated_to: "HTML_CodeSniffer has been updated to version",
        auditor_view_the_changelog: "View the changelog",
        auditor_success_criterion: "Success Criterion",
        auditor_suggested_techniques: "Suggested Techniques",
        auditor_applies_entire_document: "This applies to the entire document",
        "1_1_1_H30.2": "Img element is the only content of the link, but is missing alt text. The alt text should describe the purpose of the link.",
        "1_1_1_H67.1": "Img element with empty alt text must have absent or empty title attribute.",
        "1_1_1_H67.2": "Img element is marked so that it is ignored by Assistive Technology.",
        "1_1_1_H37": "Img element missing an alt attribute. Use the alt attribute to specify a short text alternative.",
        "1_1_1_G94.Image": "Ensure that the img element's alt text serves the same purpose and presents the same information as the image.",
        "1_1_1_H36": "Image submit button missing an alt attribute. Specify a text alternative that describes the button's function, using the alt attribute.",
        "1_1_1_G94.Button": "Ensure that the image submit button's alt text identifies the purpose of the button.",
        "1_1_1_H24": "Area element in an image map missing an alt attribute. Each area element must have a text alternative that describes the function of the image map area.",
        "1_1_1_H24.2": "Ensure that the area element's text alternative serves the same purpose as the part of image map image it references.",
        "1_1_1_G73,G74": "If this image cannot be fully described in a short text alternative, ensure a long text alternative is also available, such as in the body text or through a link.",
        "1_1_1_H2.EG5": "Img element inside a link must not use alt text that duplicates the text content of the link.",
        "1_1_1_H2.EG4": "Img element inside a link has empty or missing alt text when a link beside it contains link text. Consider combining the links.",
        "1_1_1_H2.EG3": "Img element inside a link must not use alt text that duplicates the content of a text link beside it.",
        "1_1_1_H53,ARIA6": "Object elements must contain a text alternative after all other alternatives are exhausted.",
        "1_1_1_G94,G92.Object,ARIA6": "Check that short (and if appropriate, long) text alternatives are available for non-text content that serve the same purpose and present the same information.",
        "1_1_1_H35.3": "Applet elements must contain a text alternative in the element's body, for browsers without support for the applet element.",
        "1_1_1_H35.2": "Applet elements must contain an alt attribute, to provide a text alternative to browsers supporting the element but are unable to load the applet.",
        "1_1_1_G94,G92.Applet": "Check that short (and if appropriate, long) text alternatives are available for non-text content that serve the same purpose and present the same information.",
        "1_2_1_G158": "If this embedded object contains pre-recorded audio only, and is not provided as an alternative for text content, check that an alternative text version is available.",
        "1_2_1_G159,G166": "If this embedded object contains pre-recorded video only, and is not provided as an alternative for text content, check that an alternative text version is available, or an audio track is provided that presents equivalent information.",
        "1_2_2_G87,G93": "If this embedded object contains pre-recorded synchronised media and is not provided as an alternative for text content, check that captions are provided for audio content.",
        "1_2_3_G69,G78,G173,G8": "If this embedded object contains pre-recorded synchronised media and is not provided as an alternative for text content, check that an audio description of its video, and/or an alternative text version of the content is provided.",
        "1_2_4_G9,G87,G93": "If this embedded object contains synchronised media, check that captions are provided for live audio content.",
        "1_2_5_G78,G173,G8": "If this embedded object contains pre-recorded synchronised media, check that an audio description is provided for its video content.",
        "1_2_6_G54,G81": "If this embedded object contains pre-recorded synchronised media, check that a sign language interpretation is provided for its audio.",
        "1_2_7_G8": "If this embedded object contains synchronised media, and where pauses in foreground audio is not sufficient to allow audio descriptions to convey the sense of pre-recorded video, check that an extended audio description is provided, either through scripting or an alternate version.",
        "1_2_8_G69,G159": "If this embedded object contains pre-recorded synchronised media or video-only content, check that an alternative text version of the content is provided.",
        "1_2_9_G150,G151,G157": "If this embedded object contains live audio-only content, check that an alternative text version of the content is provided.",
        "1_3_1_F92,ARIA4": 'This element\'s role is "presentation" but contains child elements with semantic meaning.',
        "1_3_1_H44.NonExistent": 'This label\'s "for" attribute contains an ID that does not exist in the document.',
        "1_3_1_H44.NonExistentFragment": 'This label\'s "for" attribute contains an ID that does not exist in the document fragment.',
        "1_3_1_H44.NotFormControl": 'This label\'s "for" attribute contains an ID for an element that is not a form control. Ensure that you have entered the correct ID for the intended element.',
        "1_3_1_H65": 'This form control has a "title" attribute that is empty or contains only spaces. It will be ignored for labelling test purposes.',
        "1_3_1_ARIA6": 'This form control has an "aria-label" attribute that is empty or contains only spaces. It will be ignored for labelling test purposes.',
        "1_3_1_ARIA16,ARIA9": 'This form control contains an aria-labelledby attribute, however it includes an ID "{{id}}" that does not exist on an element. The aria-labelledby attribute will be ignored for labelling test purposes.',
        "1_3_1_F68.Hidden": "This hidden form field is labelled in some way. There should be no need to label a hidden form field.",
        "1_3_1_F68.HiddenAttr": 'This form field is intended to be hidden (using the "hidden" attribute), but is also labelled in some way. There should be no need to label a hidden form field.',
        "1_3_1_F68": 'This form field should be labelled in some way. Use the label element (either with a "for" attribute or wrapped around the form field), or "title", "aria-label" or "aria-labelledby" attributes as appropriate.',
        "1_3_1_H49.": "Presentational markup used that has become obsolete in HTML5.",
        "1_3_1_H49.AlignAttr": "Align attributes.",
        "1_3_1_H49.Semantic": "Semantic markup should be used to mark emphasised or special text so that it can be programmatically determined.",
        "1_3_1_H49.AlignAttr.Semantic": "Semantic markup should be used to mark emphasised or special text so that it can be programmatically determined.",
        "1_3_1_H42": "Heading markup should be used if this content is intended as a heading.",
        "1_3_1_H63.3": "Table cell has an invalid scope attribute. Valid values are row, col, rowgroup, or colgroup.",
        "1_3_1_H63.2": "Scope attributes on td elements that act as headings for other elements are obsolete in HTML5. Use a th element instead.",
        "1_3_1_H43.ScopeAmbiguous": "Scope attributes on th elements are ambiguous in a table with multiple levels of headings. Use the headers attribute on td elements instead.",
        "1_3_1_H43.IncorrectAttr": 'Incorrect headers attribute on this td element. Expected "{{expected}}" but found "{{actual}}"',
        "1_3_1_H43.HeadersRequired": "The relationship between td elements and their associated th elements is not defined. As this table has multiple levels of th elements, you must use the headers attribute on td elements.",
        "1_3_1_H43.MissingHeaderIds": "Not all th elements in this table contain an id attribute. These cells should contain ids so that they may be referenced by td elements' headers attributes.",
        "1_3_1_H43.MissingHeadersAttrs": "Not all td elements in this table contain a headers attribute. Each headers attribute should list the ids of all th elements associated with that cell.",
        "1_3_1_H43,H63": "The relationship between td elements and their associated th elements is not defined. Use either the scope attribute on th elements, or the headers attribute on td elements.",
        "1_3_1_H63.1": "Not all th elements in this table have a scope attribute. These cells should contain a scope attribute to identify their association with td elements.",
        "1_3_1_H73.3.LayoutTable": "This table appears to be used for layout, but contains a summary attribute. Layout tables must not contain summary attributes, or if supplied, must be empty.",
        "1_3_1_H39,H73.4": "If this table is a data table, and both a summary attribute and a caption element are present, the summary should not duplicate the caption.",
        "1_3_1_H73.3.Check": "If this table is a data table, check that the summary attribute describes the table's organization or explains how to use the table.",
        "1_3_1_H73.3.NoSummary": "If this table is a data table, consider using the summary attribute of the table element to give an overview of this table.",
        "1_3_1_H39.3.LayoutTable": "This table appears to be used for layout, but contains a caption element. Layout tables must not contain captions.",
        "1_3_1_H39.3.Check": "If this table is a data table, check that the caption element accurately describes this table.",
        "1_3_1_H39.3.NoCaption": "If this table is a data table, consider using a caption element to the table element to identify this table.",
        "1_3_1_H71.NoLegend": "Fieldset does not contain a legend element. All fieldsets should contain a legend element that describes a description of the field group.",
        "1_3_1_H85.2": "If this selection list contains groups of related options, they should be grouped with optgroup.",
        "1_3_1_H71.SameName": "If these radio buttons or check boxes require a further group-level description, they should be contained within a fieldset element.",
        "1_3_1_H48.1": "This content looks like it is simulating an unordered list using plain text. If so, marking up this content with a ul element would add proper structure information to the document.",
        "1_3_1_H48.2": "This content looks like it is simulating an ordered list using plain text. If so, marking up this content with an ol element would add proper structure information to the document.",
        "1_3_1_G141_a": "The heading structure is not logically nested. This h{{headingNum}} element appears to be the primary document heading, so should be an h1 element.",
        "1_3_1_G141_b": "The heading structure is not logically nested. This h{{headingNum}} element should be an h{{properHeadingNum}} to be properly nested.",
        "1_3_1_H42.2": "Heading tag found with no content. Text that is not intended as a heading should not be marked up with heading tags.",
        "1_3_1_H48": "If this element contains a navigation section, it is recommended that it be marked up as a list.",
        "1_3_1_LayoutTable": "This table appears to be a layout table. If it is meant to instead be a data table, ensure header cells are identified using th elements.",
        "1_3_1_DataTable": "This table appears to be a data table. If it is meant to instead be a layout table, ensure there are no th elements, and no summary or caption.",
        "1_3_2_G57": "Check that the content is ordered in a meaningful sequence when linearised, such as when style sheets are disabled.",
        "1_3_3_G96": "Where instructions are provided for understanding the content, do not rely on sensory characteristics alone (such as shape, size or location) to describe objects.",
        "1_4_1_G14,G18": "Check that any information conveyed using colour alone is also available in text, or through other visual cues.",
        "1_4_2_F23": "If this element contains audio that plays automatically for longer than 3 seconds, check that there is the ability to pause, stop or mute the audio.",
        "1_4_3_F24.BGColour": "Check that this element has an inherited foreground colour to complement the corresponding inline background colour or image.",
        "1_4_3_F24.FGColour": "Check that this element has an inherited background colour or image to complement the corresponding inline foreground colour.",
        "1_4_3_G18_or_G145.Abs": "This element is absolutely positioned and the background color can not be determined. Ensure the contrast ratio between the text and all covered parts of the background are at least {{required}}:1.",
        "1_4_3_G18_or_G145.BgImage": "This element's text is placed on a background image. Ensure the contrast ratio between the text and all covered parts of the image are at least {{required}}:1.",
        "1_4_3_G18_or_G145.Alpha": "This element's text or background contains transparency. Ensure the contrast ratio between the text and background are at least {{required}}:1.",
        "1_4_3_G18_or_G145.Fail": "This element has insufficient contrast at this conformance level. Expected a contrast ratio of at least {{required}}:1, but text in this element has a contrast ratio of {{value}}:1.",
        "1_4_3_G18_or_G145.Fail.Recomendation": "Recommendation: change",
        "1_4_3_G18_or_G145.Fail.Recomendation.Text": "text colour to",
        "1_4_3_G18_or_G145.Fail.Recomendation.Background": "background to",
        "1_4_4_G142": "Check that text can be resized without assistive technology up to 200 percent without loss of content or functionality.",
        "1_4_5_G140,C22,C30.AALevel": "If the technologies being used can achieve the visual presentation, check that text is used to convey information rather than images of text, except when the image of text is essential to the information being conveyed, or can be visually customised to the user's requirements.",
        "1_4_6_G18_or_G17.Abs": "This element is absolutely positioned and the background color can not be determined. Ensure the contrast ratio between the text and all covered parts of the background are at least {{required}}:1.",
        "1_4_6_G18_or_G17.BgImage": "This element's text is placed on a background image. Ensure the contrast ratio between the text and all covered parts of the image are at least {{required}}:1.",
        "1_4_6_G18_or_G17.Fail": "This element has insufficient contrast at this conformance level. Expected a contrast ratio of at least {{required}}:1, but text in this element has a contrast ratio of {{value}}:1.",
        "1_4_6_G18_or_G17.Fail.Recomendation": "Recommendation: change",
        "1_4_6_G18_or_G17.Fail.Recomendation.Text": "text colour to",
        "1_4_6_G18_or_G17.Fail.Recomendation.Background": "background to",
        "1_4_7_G56": "For pre-recorded audio-only content in this element that is primarily speech (such as narration), any background sounds should be muteable, or be at least 20 dB (or about 4 times) quieter than the speech.",
        "1_4_8_G148,G156,G175": "Check that a mechanism is available for the user to select foreground and background colours for blocks of text, either through the Web page or the browser.",
        "1_4_8_H87,C20": "Check that a mechanism exists to reduce the width of a block of text to no more than 80 characters (or 40 in Chinese, Japanese or Korean script).",
        "1_4_8_C19,G172,G169": "Check that blocks of text are not fully justified - that is, to both left and right edges - or a mechanism exists to remove full justification.",
        "1_4_8_G188,C21": "Check that line spacing in blocks of text are at least 150% in paragraphs, and paragraph spacing is at least 1.5 times the line spacing, or that a mechanism is available to achieve this.",
        "1_4_8_H87,G146,C26": "Check that text can be resized without assistive technology up to 200 percent without requiring the user to scroll horizontally on a full-screen window.",
        "1_4_9_G140,C22,C30.NoException": "Check that images of text are only used for pure decoration or where a particular presentation of text is essential to the information being conveyed.",
        "2_1_1_G90": "Ensure the functionality provided by an event handler for this element is available through the keyboard",
        "2_1_1_SCR20.DblClick": "Ensure the functionality provided by double-clicking on this element is available through the keyboard.",
        "2_1_1_SCR20.MouseOver": "Ensure the functionality provided by mousing over this element is available through the keyboard; for instance, using the focus event.",
        "2_1_1_SCR20.MouseOut": "Ensure the functionality provided by mousing out of this element is available through the keyboard; for instance, using the blur event.",
        "2_1_1_SCR20.MouseMove": "Ensure the functionality provided by moving the mouse on this element is available through the keyboard.",
        "2_1_1_SCR20.MouseDown": "Ensure the functionality provided by mousing down on this element is available through the keyboard; for instance, using the keydown event.",
        "2_1_1_SCR20.MouseUp": "Ensure the functionality provided by mousing up on this element is available through the keyboard; for instance, using the keyup event.",
        "2_1_2_F10": "Check that this applet or plugin provides the ability to move the focus away from itself when using the keyboard.",
        "2_2_1_F40.2": "Meta refresh tag used to redirect to another page, with a time limit that is not zero. Users cannot control this time limit.",
        "2_2_1_F41.2": "Meta refresh tag used to refresh the current page. Users cannot control the time limit for this refresh.",
        "2_2_2_SCR33,SCR22,G187,G152,G186,G191": "If any part of the content moves, scrolls or blinks for more than 5 seconds, or auto-updates, check that there is a mechanism available to pause, stop, or hide the content.",
        "2_2_2_F4": "Ensure there is a mechanism available to stop this blinking element in less than five seconds.",
        "2_2_2_F47": "Blink elements cannot satisfy the requirement that blinking information can be stopped within five seconds.",
        "2_2_3_G5": "Check that timing is not an essential part of the event or activity presented by the content, except for non-interactive synchronized media and real-time events.",
        "2_2_4_SCR14": "Check that all interruptions (including updates to content) can be postponed or suppressed by the user, except interruptions involving an emergency.",
        "2_2_5_G105,G181": "If this Web page is part of a set of Web pages with an inactivity time limit, check that an authenticated user can continue the activity without loss of data after re-authenticating.",
        "2_3_1_G19,G176": "Check that no component of the content flashes more than three times in any 1-second period, or that the size of any flashing area is sufficiently small.",
        "2_3_2_G19": "Check that no component of the content flashes more than three times in any 1-second period.",
        "2_4_1_H64.1": "Iframe element requires a non-empty title attribute that identifies the frame.",
        "2_4_1_H64.2": "Check that the title attribute of this element contains text that identifies the frame.",
        "2_4_1_G1,G123,G124,H69": "Ensure that any common navigation elements can be bypassed; for instance, by use of skip links, header elements, or ARIA landmark roles.",
        "2_4_1_G1,G123,G124.NoSuchID": 'This link points to a named anchor "{{id}}" within the document, but no anchor exists with that name.',
        "2_4_1_G1,G123,G124.NoSuchIDFragment": 'This link points to a named anchor "{{id}}" within the document, but no anchor exists with that name in the fragment tested.',
        "2_4_2_H25.1.NoHeadEl": "There is no head section in which to place a descriptive title element.",
        "2_4_2_H25.1.NoTitleEl": "A title should be provided for the document, using a non-empty title element in the head section.",
        "2_4_2_H25.1.EmptyTitle": "The title element in the head section should be non-empty.",
        "2_4_2_H25.2": "Check that the title element describes the document.",
        "2_4_3_H4.2": "If tabindex is used, check that the tab order specified by the tabindex attributes follows relationships in the content.",
        "2_4_4_H77,H78,H79,H80,H81,H33": "Check that the link text combined with programmatically determined link context, or its title attribute, identifies the purpose of the link.",
        "2_4_4_H77,H78,H79,H80,H81": "Check that the link text combined with programmatically determined link context identifies the purpose of the link.",
        "2_4_5_G125,G64,G63,G161,G126,G185": "If this Web page is not part of a linear process, check that there is more than one way of locating this Web page within a set of Web pages.",
        "2_4_6_G130,G131": "Check that headings and labels describe topic or purpose.",
        "2_4_7_G149,G165,G195,C15,SCR31": "Check that there is at least one mode of operation where the keyboard focus indicator can be visually located on user interface controls.",
        "2_4_8_H59.1": "Link elements can only be located in the head section of the document.",
        "2_4_8_H59.2a": "Link element is missing a non-empty rel attribute identifying the link type.",
        "2_4_8_H59.2b": "Link element is missing a non-empty href attribute pointing to the resource being linked.",
        "2_4_9_H30": "Check that text of the link describes the purpose of the link.",
        "3_1_1_H57.2": "The html element should have a lang or xml:lang attribute which describes the language of the document.",
        "3_1_1_H57.3.Lang": "The language specified in the lang attribute of the document element does not appear to be well-formed.",
        "3_1_1_H57.3.XmlLang": "The language specified in the xml:lang attribute of the document element does not appear to be well-formed.",
        "3_1_2_H58": "Ensure that any change in language is marked using the lang and/or xml:lang attribute on an element, as appropriate.",
        "3_1_2_H58.1.Lang": "The language specified in the lang attribute of this element does not appear to be well-formed.",
        "3_1_2_H58.1.XmlLang": "The language specified in the xml:lang attribute of this element does not appear to be well-formed.",
        "3_1_3_H40,H54,H60,G62,G70": "Check that there is a mechanism available for identifying specific definitions of words or phrases used in an unusual or restricted way, including idioms and jargon.",
        "3_1_4_G102,G55,G62,H28,G97": "Check that a mechanism for identifying the expanded form or meaning of abbreviations is available.",
        "3_1_5_G86,G103,G79,G153,G160": "Where the content requires reading ability more advanced than the lower secondary education level, supplemental content or an alternative version should be provided.",
        "3_1_6_H62.1.HTML5": "Ruby element does not contain an rt element containing pronunciation information for its body text.",
        "3_1_6_H62.1.XHTML11": "Ruby element does not contain an rt element containing pronunciation information for the text inside the rb element.",
        "3_1_6_H62.2": "Ruby element does not contain rp elements, which provide extra punctuation to browsers not supporting ruby text.",
        "3_2_1_G107": "Check that a change of context does not occur when this input field receives focus.",
        "3_2_2_H32.2": 'This form does not contain a submit button, which creates issues for those who cannot submit the form using the keyboard. Submit buttons are INPUT elements with type attribute "submit" or "image", or BUTTON elements with type "submit" or omitted/invalid.',
        "3_2_3_G61": "Check that navigational mechanisms that are repeated on multiple Web pages occur in the same relative order each time they are repeated, unless a change is initiated by the user.",
        "3_2_4_G197": "Check that components that have the same functionality within this Web page are identified consistently in the set of Web pages to which it belongs.",
        "3_2_5_H83.3": "Check that this link's link text contains information indicating that the link will open in a new window.",
        "3_3_1_G83,G84,G85": "If an input error is automatically detected in this form, check that the item(s) in error are identified and the error(s) are described to the user in text.",
        "3_3_2_G131,G89,G184,H90": "Check that descriptive labels or instructions (including for required fields) are provided for user input in this form.",
        "3_3_3_G177": "Check that this form provides suggested corrections to errors in user input, unless it would jeopardize the security or purpose of the content.",
        "3_3_4_G98,G99,G155,G164,G168.LegalForms": "If this form would bind a user to a financial or legal commitment, modify/delete user-controllable data, or submit test responses, ensure that submissions are either reversible, checked for input errors, and/or confirmed by the user.",
        "3_3_5_G71,G184,G193": "Check that context-sensitive help is available for this form, at a Web-page and/or control level.",
        "3_3_6_G98,G99,G155,G164,G168.AllForms": "Check that submissions to this form are either reversible, checked for input errors, and/or confirmed by the user.",
        "4_1_1_F77": 'Duplicate id attribute value "{{id}}" found on the web page.',
        "4_1_2_H91.A.Empty": "Anchor element found with an ID but without a href or link text. Consider moving its ID to a parent or nearby element.",
        "4_1_2_H91.A.EmptyWithName": "Anchor element found with a name attribute but without a href or link text. Consider moving the name attribute to become an ID of a parent or nearby element.",
        "4_1_2_H91.A.EmptyNoId": "Anchor element found with no link content and no name and/or ID attribute.",
        "4_1_2_H91.A.NoHref": "Anchor elements should not be used for defining in-page link targets. If not using the ID for other purposes (such as CSS or scripting), consider moving it to a parent element.",
        "4_1_2_H91.A.Placeholder": "Anchor element found with link content, but no href, ID or name attribute has been supplied.",
        "4_1_2_H91.A.NoContent": "Anchor element found with a valid href attribute, but no link content has been supplied.",
        "4_1_2_input_element": "input element",
        "4_1_2_role_of_button": 'element has a role of "button" but',
        "4_1_2_element_content": "element content",
        "4_1_2_element": "element",
        "4_1_2_msg_pattern": "This {{msgNodeType}} does not have a name available to an accessibility API. Valid names are: {{builtAttrs}}.",
        "4_1_2_msg_pattern2": "This {{msgNodeType}} does not have a value available to an accessibility API.",
        "4_1_2_msg_add_one": "Add one by adding content to the element.",
        "4_1_2_msg_pattern3": "This {{msgNodeType}} does not have an initially selected option. Depending on your HTML version, the value exposed to an accessibility API may be undefined.",
        "4_1_2_value_exposed_using_attribute": "A value is exposed using the {{requiredValue}} attribute.",
        "4_1_2_value_exposed_using_element": "A value is exposed using the {{requiredValue}} element."
    }, _global.translation.fr = {
        auditor_name: "HTML_CodeSniffer par Squiz",
        auditor_using_standard: "Utilisation de la norme",
        auditor_standards: "Normes",
        auditor_code_snippet: "Bout de code",
        auditor_close: "Fermer",
        auditor_select_types: "Sélectionner les types de questions à inclure dans le rapport",
        auditor_home: "Accueil",
        auditor_view_report: "Voir le rapport",
        auditor_report: "Rapport",
        auditor_back_to_report: "Retour au rapport",
        auditor_previous_issue: "Problème précédent",
        auditor_next_issue: "Prochain problème",
        auditor_issue: "Problème",
        auditor_of: "de",
        auditor_errors: "Erreurs",
        auditor_error: "Erreur",
        auditor_warnings: "Attentions",
        auditor_warning: "Attention",
        auditor_notices: "Avis",
        auditor_notice: "Avis",
        auditor_toggle_display_of: "Basculer l'affichage de",
        auditor_messages: "messages",
        auditor_unable_to_point: "Impossible de pointer vers l'élément associé à ce problème.",
        auditor_unable_to_point_entire: "Impossible d'attirer l'attention sur cette question, car elle concerne l'ensemble du document.",
        auditor_unable_to_point_removed: "Impossible de pointer vers cet élément car il a été supprimé du document depuis que le rapport a été généré.",
        auditor_unable_to_point_outside: "Impossible de pointer vers cet élément parce qu'il est situé à l'extérieur de l'élément de corps du document.",
        auditor_unable_to_point_hidden: "Impossible de pointer vers cet élément parce qu'il est caché de la vue ou n'a pas de représentation visuelle.",
        auditor_point_to_element: "Pointer vers l'élément",
        auditor_unsupported_browser: "La fonctionnalité d'extrait de code n'est pas prise en charge dans ce navigateur.",
        auditor_page: "Page",
        auditor_updated_to: "HTML_CodeSniffer a été mis à jour en version",
        auditor_view_the_changelog: "Voir le journal des modifications",
        auditor_success_criterion: "Critère de réussite",
        auditor_suggested_techniques: "Techniques suggérées",
        auditor_applies_entire_document: "Ceci s'applique à l'ensemble du document",
        "1_1_1_H30.2": "L'élément Img est le seul contenu du lien, mais il manque le texte alt. Le texte alternatif devrait décrire le but du lien.",
        "1_1_1_H67.1": "L'élément Img avec du texte alt vide doit avoir un attribut de titre absent ou vide.",
        "1_1_1_H67.2": "L'élément Img est marqué de sorte qu'il est ignoré par la technologie d'assistance.",
        "1_1_1_H37": "Élément Img auquel il manque un attribut alt. Utilisez l'attribut alt pour spécifier une alternative de texte court.",
        "1_1_1_G94.Image": "Assurez-vous que le texte alt de l'élément img sert aux mêmes fins et présente les mêmes informations que l'image.",
        "1_1_1_H36": "Le bouton de soumission d'image n'a pas de texte alternatif. Spécifiez une alternative de texte qui décrit la fonction du bouton, en utilisant l'attribut alt.",
        "1_1_1_G94.Button": "Assurez-vous que le texte alt du bouton de soumission d'image identifie le but du bouton.",
        "1_1_1_H24": "Élément de zone dans une carte-image sans attribut alt. Chaque élément de zone doit avoir une alternative textuelle qui décrit la fonction de la zone de la carte image.",
        "1_1_1_H24.2": "Assurez-vous que l'alternative textuelle de l'élément de zone sert le même but que la partie de l'image de la carte-image à laquelle il fait référence.",
        "1_1_1_G73,G74": "Si cette image ne peut être entièrement décrite dans un texte court, assurez-vous qu'un texte long est également disponible, comme dans le corps du texte ou par le biais d'un lien.",
        "1_1_1_H2.EG5": "L'élément Img à l'intérieur d'un lien ne doit pas utiliser de texte alt qui duplique le contenu textuel du lien.",
        "1_1_1_H2.EG4": "L'élément Img à l'intérieur d'un lien a du texte alt vide ou manquant lorsqu'un lien à côté contient du texte de lien. Pensez à combiner les liens.",
        "1_1_1_H2.EG3": "L'élément Img à l'intérieur d'un lien ne doit pas utiliser de texte alt qui duplique le contenu d'un lien texte à côté.",
        "1_1_1_H53,ARIA6": "Les éléments d'objet doivent contenir une alternative de texte après l'épuisement de toutes les autres alternatives.",
        "1_1_1_G94,G92.Object,ARIA6": "Vérifiez que des textes courts (et, le cas échéant, les longs) sont disponibles pour les contenus non textuels qui servent le même but et présentent la même information.",
        "1_1_1_H35.3": "Les éléments de l'applet doivent contenir une alternative textuelle dans le corps de l'élément, pour les navigateurs qui ne supportent pas l'élément applet.",
        "1_1_1_H35.2": "Les éléments de l'applet doivent contenir un attribut alt, afin de fournir une alternative textuelle aux navigateurs supportant l'élément mais incapables de charger l'applet.",
        "1_1_1_G94,G92.Applet": "Vérifiez que des textes courts (et, le cas échéant, les longs) sont disponibles pour les contenus non textuels qui servent le même but et présentent la même information.",
        "1_2_1_G158": "Si cet objet incorporé ne contient que de l'audio préenregistré et n'est pas fourni comme alternative pour le contenu textuel, vérifiez qu'une version texte alternative est disponible.",
        "1_2_1_G159,G166": "Si cet objet incorporé ne contient que de la vidéo préenregistrée et n'est pas fourni comme alternative au contenu textuel, vérifiez qu'une version texte alternative est disponible, ou qu'une piste audio est fournie qui présente des informations équivalentes.",
        "1_2_2_G87,G93": "Si cet objet incorporé contient un support synchronisé préenregistré et n'est pas fourni comme alternative pour le contenu textuel, vérifiez que les légendes sont fournies pour le contenu audio.",
        "1_2_3_G69,G78,G173,G8": "Si cet objet incorporé contient un support synchronisé préenregistré et n'est pas fourni comme alternative au contenu textuel, vérifiez qu'une description audio de sa vidéo et/ou une version textuelle alternative du contenu est fournie.",
        "1_2_4_G9,G87,G93": "Si cet objet incorporé contient des médias synchronisés, vérifiez que les légendes sont fournies pour le contenu audio en direct.",
        "1_2_5_G78,G173,G8": "Si cet objet incorporé contient un support synchronisé préenregistré, vérifiez qu'une description audio est fournie pour son contenu vidéo.",
        "1_2_6_G54,G81": "Si cet objet incorporé contient un support synchronisé préenregistré, vérifiez qu'une interprétation en langage des signes est fournie pour l'audio.",
        "1_2_7_G8": "Si cet objet incorporé contient des médias synchronisés, et si les pauses dans l'audio de premier plan ne suffisent pas pour permettre aux descriptions audio de transmettre le sens de la vidéo préenregistrée, vérifiez qu'une description audio étendue est fournie, soit par le biais d'un script ou d'une autre version.",
        "1_2_8_G69,G159": "Si cet objet incorporé contient un média synchronisé pré-enregistré ou un contenu vidéo uniquement, vérifiez qu'une version texte alternative du contenu est fournie.",
        "1_2_9_G150,G151,G157": "Si cet objet incorporé contient du contenu audio en direct, vérifiez qu'une version texte alternative du contenu est fournie.",
        "1_3_1_F92,ARIA4": 'Le rôle de cet élément est "présentation" mais contient des éléments enfants avec une signification sémantique.',
        "1_3_1_H44.NonExistent": "L'attribut \"for\" de cette étiquette contient un identifiant qui n'existe pas dans le document.",
        "1_3_1_H44.NonExistentFragment": "L'attribut \"for\" de cette étiquette contient un ID qui n'existe pas dans le fragment de document.",
        "1_3_1_H44.NotFormControl": "L'attribut \"for\" de cette étiquette contient un ID pour un élément qui n'est pas un contrôle de formulaire. Assurez-vous d'avoir saisi l'ID correct pour l'élément prévu.",
        "1_3_1_H65": 'Ce contrôle de formulaire a un attribut "title" qui est vide ou ne contient que des espaces. Il sera ignoré à des fins de test d\'étiquetage.',
        "1_3_1_ARIA6": 'Ce contrôle de formulaire possède un attribut "aria-label" qui est vide ou ne contient que des espaces. Il sera ignoré à des fins de test d\'étiquetage.',
        "1_3_1_ARIA16,ARIA9": "Ce contrôle de formulaire contient un attribut aria-labelledby, mais il inclut un ID \"{{id}}\" qui n'existe pas sur un élément. L'attribut aria-labelledby sera ignoré à des fins de test d'étiquetage.",
        "1_3_1_F68.Hidden": "Ce champ de formulaire caché est étiqueté d'une manière ou d'une autre. Il ne devrait pas être nécessaire d'étiqueter un champ de formulaire caché.",
        "1_3_1_F68.HiddenAttr": "Ce champ de formulaire est destiné à être masqué (à l'aide de l'attribut \"caché\"), mais il est également étiqueté d'une manière ou d'une autre. Il ne devrait pas être nécessaire d'étiqueter un champ de formulaire caché.",
        "1_3_1_F68": 'Ce champ du formulaire doit être étiqueté d\'une manière ou d\'une autre. Utilisez l\'élément d\'étiquette (avec un attribut "for" ou enroulé autour du champ du formulaire), ou les attributs "title", "aria-label" ou "aria-labelledby" selon le cas.',
        "1_3_1_H49.": "Le balisage de présentation utilisé est devenu obsolète dans HTML5.",
        "1_3_1_H49.AlignAttr": "Aligner les attributs.",
        "1_3_1_H49.Semantic": "Le balisage sémantique doit être utilisé pour marquer un texte accentué ou un texte spécial afin qu'il puisse être déterminé par programmation.",
        "1_3_1_H49.AlignAttr.Semantic": "Le balisage sémantique doit être utilisé pour marquer un texte accentué ou un texte spécial afin qu'il puisse être déterminé par programmation.",
        "1_3_1_H42": "Une balise d'en-tête doit être utilisée si ce contenu est destiné à servir d'en-tête.",
        "1_3_1_H63.3": "La cellule de table a un attribut de portée invalide. Les valeurs valides sont ligne, col, groupe de lignes, groupe de lignes ou groupe de colonnes.",
        "1_3_1_H63.2": "Les attributs Scope sur les éléments td qui servent de titres pour d'autres éléments sont obsolètes dans HTML5. Utilisez un th élément à la place.",
        "1_3_1_H43.ScopeAmbiguous": "Les attributs de portée sur ces éléments sont ambigus dans un tableau à niveaux multiples d'en-têtes. Utilisez plutôt l'attribut headers sur les éléments td.",
        "1_3_1_H43.IncorrectAttr": 'L\'attribut d\'en-tête incorrect sur cet élément td. Attendue "{{expected}}" mais trouvée "{{actual}}".',
        "1_3_1_H43.HeadersRequired": "La relation entre les éléments td et leurs éléments associés n'est pas définie. Comme cette table a plusieurs niveaux de ces éléments, vous devez utiliser l'attribut headers sur les éléments td.",
        "1_3_1_H43.MissingHeaderIds": "Tous les éléments de cette table ne contiennent pas un attribut id. Ces cellules devraient contenir des ids de sorte qu'elles puissent être référencées par des éléments td attributs d'en-têtes.",
        "1_3_1_H43.MissingHeadersAttrs": "Tous les éléments td de cette table ne contiennent pas un attribut d'en-tête. Chaque attribut d'en-tête devrait énumérer les ids de tous les éléments associés à cette cellule.",
        "1_3_1_H43,H63": "La relation entre les éléments td et leurs éléments associés n'est pas définie. Utilisez soit l'attribut scope sur ces éléments, soit l'attribut headers sur les éléments td.",
        "1_3_1_H63.1": "Tous les éléments de ce tableau n'ont pas tous un attribut de portée. Ces cellules doivent contenir un attribut scope pour identifier leur association avec les éléments td.",
        "1_3_1_H73.3.LayoutTable": "Ce tableau semble être utilisé pour la mise en page, mais contient un attribut résumé. Les tableaux de présentation ne doivent pas contenir d'attributs sommaires ou, s'ils sont fournis, doivent être vides.",
        "1_3_1_H39,H73.4": "Si ce tableau est un tableau de données et qu'un attribut résumé et un élément de légende sont présents, le résumé ne doit pas dupliquer la légende.",
        "1_3_1_H73.3.Check": "Si ce tableau est un tableau de données, vérifiez que l'attribut summary décrit l'organisation du tableau ou explique comment utiliser le tableau.",
        "1_3_1_H73.3.NoSummary": "Si ce tableau est un tableau de données, envisagez d'utiliser l'attribut résumé de l'élément de tableau pour donner une vue d'ensemble de ce tableau.",
        "1_3_1_H39.3.LayoutTable": "Ce tableau semble être utilisé pour la mise en page, mais contient un élément de légende. Les tables de présentation ne doivent pas contenir de légendes.",
        "1_3_1_H39.3.Check": "Si ce tableau est un tableau de données, vérifiez que l'élément de légende décrit correctement ce tableau.",
        "1_3_1_H39.3.NoCaption": "Si ce tableau est un tableau de données, envisagez d'utiliser un élément de légende de l'élément de tableau pour identifier ce tableau.",
        "1_3_1_H71.NoLegend": "Fieldset ne contient pas d'élément de légende. Tous les champs doivent contenir un élément de légende décrivant la description du groupe de champs.",
        "1_3_1_H85.2": "Si cette liste de sélection contient des groupes d'options connexes, ils doivent être regroupés avec le groupe optgroup.",
        "1_3_1_H71.SameName": "Si ces boutons radio ou cases à cocher nécessitent une description plus détaillée au niveau du groupe, ils doivent être contenus dans un élément de l'ensemble des champs.",
        "1_3_1_H48.1": "Ce contenu semble simuler une liste non ordonnée à l'aide de texte brut. Si c'est le cas, marquer ce contenu avec un élément ul ajouterait une information de structure appropriée au document.",
        "1_3_1_H48.2": "Ce contenu semble simuler une liste ordonnée à l'aide de texte brut. Si c'est le cas, marquer ce contenu avec un élément ol ajouterait des informations de structure appropriées au document.",
        "1_3_1_G141_a": "La structure d'en-tête n'est pas imbriquée logiquement. Cet élément h{{{headingNum}} semble être l'en-tête du document primaire, donc devrait être un élément h1.",
        "1_3_1_G141_b": "La structure d'en-tête n'est pas imbriquée logiquement. Cet élément h{{{headingNum}} devrait être un h{properHeadingNum}} pour être correctement imbriqué.",
        "1_3_1_H42.2": "Étiquette d'en-tête trouvée sans contenu. Le texte qui n'est pas destiné à servir d'en-tête ne doit pas être marqué avec des balises d'en-tête.",
        "1_3_1_H48": "Si cet élément contient une section de navigation, il est recommandé de le marquer comme une liste.",
        "1_3_1_LayoutTable": "Ce tableau semble être un tableau de présentation. S'il s'agit plutôt d'un tableau de données, assurez-vous que les cellules d'en-tête sont identifiées à l'aide de ces éléments.",
        "1_3_1_DataTable": "Ce tableau semble être un tableau de données. S'il s'agit plutôt d'un tableau de présentation, assurez-vous qu'il n'y a pas d'éléments, ni de résumé ou de légende\".",
        "1_3_2_G57": "Vérifiez que le contenu est ordonné dans un ordre significatif lorsqu'il est linéarisé, par exemple lorsque les feuilles de style sont désactivées.",
        "1_3_3_G96": "Lorsque des instructions sont fournies pour comprendre le contenu, ne vous fiez pas uniquement aux caractéristiques sensorielles (telles que la forme, la taille ou l'emplacement) pour décrire les objets.",
        "1_4_1_G14,G18": "Vérifier que toute information véhiculée par la couleur seule est également disponible sous forme de texte ou d'autres repères visuels.",
        "1_4_2_F23": "Si cet élément contient de l'audio qui joue automatiquement pendant plus de 3 secondes, vérifiez qu'il est possible de mettre en pause, d'arrêter ou de couper le son.",
        "1_4_3_F24.BGColour": "Vérifiez que cet élément a une couleur d'avant-plan héritée pour compléter la couleur ou l'image d'arrière-plan en ligne correspondante.",
        "1_4_3_F24.FGColour": "Vérifiez que cet élément a une couleur ou une image d'arrière-plan héritée pour compléter la couleur d'avant-plan correspondante.",
        "1_4_3_G18_or_G145.Abs": "Cet élément est absolument positionné et la couleur de fond ne peut pas être déterminée. Assurez-vous que le rapport de contraste entre le texte et toutes les parties couvertes de l'arrière-plan est d'au moins {{nécessaire}}:1.",
        "1_4_3_G18_or_G145.BgImage": "Le texte de cet élément est placé sur une image de fond. Assurez-vous que le rapport de contraste entre le texte et toutes les parties couvertes de l'image est d'au moins {{nécessaire}}:1.",
        "1_4_3_G18_or_G145.Alpha": "Le texte ou l'arrière-plan de cet élément contient de la transparence. Assurez-vous que le rapport de contraste entre le texte et l'arrière-plan est d'au moins {{nécessaire}}:1.",
        "1_4_3_G18_or_G145.Fail": "Cet élément a un contraste insuffisant à ce niveau de conformité. On s'attendait à un rapport de contraste d'au moins {{required}}:1, mais le texte dans cet élément a un rapport de contraste de {{value}}:1.",
        "1_4_3_G18_or_G145.Fail.Recomendation": "Recommandation : changement",
        "1_4_3_G18_or_G145.Fail.Recomendation.Text": "Couleur du texte à",
        "1_4_3_G18_or_G145.Fail.Recomendation.Background": "Fond à",
        "1_4_4_G142": "Vérifiez que le texte peut être redimensionné sans technologie d'assistance jusqu'à 200 pour cent sans perte de contenu ou de fonctionnalité.",
        "1_4_5_G140,C22,C30.AALevel": "Si les technologies utilisées permettent d'obtenir une présentation visuelle, vérifiez que le texte est utilisé pour transmettre des informations plutôt que des images de texte, sauf lorsque l'image du texte est essentielle à l'information véhiculée, ou peut être visuellement adaptée aux besoins de l'utilisateur.",
        "1_4_6_G18_or_G17.Abs": "Cet élément est absolument positionné et la couleur de fond ne peut pas être déterminée. Assurez-vous que le rapport de contraste entre le texte et toutes les parties couvertes de l'arrière-plan est d'au moins {{nécessaire}}:1.",
        "1_4_6_G18_or_G17.BgImage": "Le texte de cet élément est placé sur une image de fond. Assurez-vous que le rapport de contraste entre le texte et toutes les parties couvertes de l'image est d'au moins {{nécessaire}}:1.",
        "1_4_6_G18_or_G17.Fail": "Cet élément a un contraste insuffisant à ce niveau de conformité. On s'attendait à un rapport de contraste d'au moins {{required}}:1, mais le texte dans cet élément a un rapport de contraste de {{value}}:1.",
        "1_4_6_G18_or_G17.Fail.Recomendation": "Recommandation : changement",
        "1_4_6_G18_or_G17.Fail.Recomendation.Text": "Couleur du texte à",
        "1_4_6_G18_or_G17.Fail.Recomendation.Background": "Fond à",
        "1_4_7_G56": "Pour le contenu audio préenregistré de cet élément qui est principalement de la parole (comme la narration), tout bruit de fond devrait être muet, ou être au moins 20 dB (ou environ 4 fois plus silencieux que le discours).",
        "1_4_8_G148,G156,G175": "Vérifiez qu'il existe un mécanisme permettant à l'utilisateur de sélectionner les couleurs d'avant-plan et d'arrière-plan pour les blocs de texte, soit par l'intermédiaire de la page Web ou du navigateur.",
        "1_4_8_H87,C20": "Vérifiez qu'il existe un mécanisme permettant de réduire la largeur d'un bloc de texte à un maximum de 80 caractères (ou 40 en caractères chinois, japonais ou coréen).",
        "1_4_8_C19,G172,G169": "Vérifiez que les blocs de texte ne sont pas entièrement justifiés - c'est-à-dire à gauche et à droite - ou qu'il existe un mécanisme pour supprimer toute justification.",
        "1_4_8_G188,C21": "Vérifiez que l'interligne dans les blocs de texte est d'au moins 150% dans les paragraphes et que l'interligne est d'au moins 1,5 fois l'interligne ou qu'il existe un mécanisme pour y parvenir.",
        "1_4_8_H87,G146,C26": "Vérifiez que le texte peut être redimensionné sans technologie d'assistance jusqu'à 200 pour cent sans que l'utilisateur ait besoin de faire défiler horizontalement sur une fenêtre plein écran.",
        "1_4_9_G140,C22,C30.NoException": "Vérifier que les images de texte ne sont utilisées qu'à des fins de décoration pure ou lorsqu'une présentation particulière du texte est essentielle à l'information véhiculée.",
        "2_1_1_G90": "S'assurer que la fonctionnalité fournie par un gestionnaire d'événements pour cet élément est disponible par l'intermédiaire du clavier.",
        "2_1_1_SCR20.DblClick": "Assurez-vous que la fonctionnalité fournie en double-cliquant sur cet élément est disponible par l'intermédiaire du clavier.",
        "2_1_1_SCR20.MouseOver": "Assurez-vous que la fonctionnalité fournie par la souris sur cet élément est disponible par l'intermédiaire du clavier, par exemple, en utilisant l'événement focus.",
        "2_1_1_SCR20.MouseOut": "Assurez-vous que la fonctionnalité fournie par la souris hors de cet élément est disponible par le clavier ; par exemple, en utilisant l'événement flou.",
        "2_1_1_SCR20.MouseMove": "Assurez-vous que la fonctionnalité fournie en déplaçant la souris sur cet élément est disponible par l'intermédiaire du clavier.",
        "2_1_1_SCR20.MouseDown": "Assurez-vous que la fonctionnalité fournie par la souris sur cet élément est disponible par l'intermédiaire du clavier, par exemple, en utilisant l'événement keydown.",
        "2_1_1_SCR20.MouseUp": "Assurez-vous que la fonctionnalité fournie par la souris sur cet élément est disponible par l'intermédiaire du clavier, par exemple, en utilisant l'événement keyup.",
        "2_1_2_F10": "Vérifiez que cette applet ou plugin permet d'éloigner le focus de lui-même lors de l'utilisation du clavier.",
        "2_2_1_F40.2": "Meta refresh tag utilisé pour rediriger vers une autre page, avec une limite de temps qui n'est pas nulle. Les utilisateurs ne peuvent pas contrôler cette limite de temps.",
        "2_2_1_F41.2": "Meta refresh tag utilisé pour rafraîchir la page courante. Les utilisateurs ne peuvent pas contrôler la limite de temps pour ce rafraîchissement.",
        "2_2_2_SCR33,SCR22,G187,G152,G186,G191": "Si une partie du contenu bouge, défile ou clignote pendant plus de 5 secondes, ou se met à jour automatiquement, vérifiez qu'il existe un mécanisme permettant de mettre en pause, d'arrêter ou de cacher le contenu.",
        "2_2_2_F4": "S'assurer qu'il existe un mécanisme permettant d'arrêter cet élément clignotant en moins de cinq secondes.",
        "2_2_2_F47": "Les éléments clignotants ne peuvent pas satisfaire à l'exigence selon laquelle les informations clignotantes peuvent être arrêtées en moins de cinq secondes.",
        "2_2_3_G5": "Vérifier que le chronométrage n'est pas une partie essentielle de l'événement ou de l'activité présentée par le contenu, à l'exception des médias synchronisés non interactifs et des événements en temps réel.",
        "2_2_4_SCR14": "Vérifier que toutes les interruptions (y compris les mises à jour du contenu) peuvent être reportées ou supprimées par l'utilisateur, à l'exception des interruptions impliquant une situation d'urgence.",
        "2_2_5_G105,G181": "Si cette page Web fait partie d'un ensemble de pages Web avec une limite de temps d'inactivité, vérifiez qu'un utilisateur authentifié peut poursuivre l'activité sans perte de données après la ré-authentification.",
        "2_3_1_G19,G176": "Vérifier qu'aucun composant du contenu ne clignote plus de trois fois au cours d'une période d'une seconde ou que la taille de la zone de clignotement est suffisamment petite.",
        "2_3_2_G19": "Vérifiez qu'aucun composant du contenu ne clignote plus de trois fois au cours d'une période d'une seconde.",
        "2_4_1_H64.1": "L'élément Iframe nécessite un attribut de titre non vide qui identifie la trame.",
        "2_4_1_H64.2": "Vérifiez que l'attribut title de cet élément contient du texte qui identifie le cadre.",
        "2_4_1_G1,G123,G124,H69": "Veiller à ce que tous les éléments de navigation communs puissent être contournés ; par exemple, en utilisant des liens de saut, des éléments d'en-tête ou des rôles de repère ARIA.",
        "2_4_1_G1,G123,G124.NoSuchID": 'Ce lien pointe vers une ancre nommée "{{id}}" dans le document, mais aucune ancre n\'existe avec ce nom.',
        "2_4_1_G1,G123,G124.NoSuchIDFragment": 'Ce lien pointe vers une ancre nommée "{{id}}" dans le document, mais aucune ancre n\'existe avec ce nom dans le fragment testé.',
        "2_4_2_H25.1.NoHeadEl": "Il n'y a pas de section d'en-tête dans laquelle placer un élément de titre descriptif.",
        "2_4_2_H25.1.NoTitleEl": "Un titre devrait être fourni pour le document, en utilisant un élément de titre non vide dans la section d'en-tête.",
        "2_4_2_H25.1.EmptyTitle": "L'élément de titre de la section d'en-tête ne doit pas être vide.",
        "2_4_2_H25.2": "Vérifier que l'élément de titre décrit le document.",
        "2_4_3_H4.2": "Si tabindex est utilisé, vérifiez que l'ordre des onglets spécifié par les attributs de tabindex suit les relations dans le contenu.",
        "2_4_4_H77,H78,H79,H80,H81,H33": "Vérifiez que le texte du lien combiné avec le contexte du lien déterminé par le programme, ou son attribut de titre, identifie le but du lien.",
        "2_4_4_H77,H78,H79,H80,H81": "Vérifiez que le texte du lien combiné avec le contexte du lien déterminé par le programme identifie le but du lien.",
        "2_4_5_G125,G64,G63,G161,G126,G185": "Si cette page Web ne fait pas partie d'un processus linéaire, vérifiez qu'il existe plus d'une façon de localiser cette page Web dans un ensemble de pages Web.",
        "2_4_6_G130,G131": "Vérifiez que les en-têtes et les étiquettes décrivent le sujet ou le but.",
        "2_4_7_G149,G165,G195,C15,SCR31": "Vérifiez qu'il existe au moins un mode de fonctionnement dans lequel l'indicateur de mise au point du clavier peut être placé visuellement sur les commandes de l'interface utilisateur.",
        "2_4_8_H59.1": "Les éléments de lien ne peuvent être situés que dans la section d'en-tête du document.",
        "2_4_8_H59.2a": "Il manque à l'élément Link un attribut rel non vide identifiant le type de lien.",
        "2_4_8_H59.2b": "L'élément Link manque un attribut href non vide pointant vers la ressource liée.",
        "2_4_9_H30": "Vérifiez que le texte du lien décrit l'objet du lien.",
        "3_1_1_H57.2": "L'élément html doit avoir un attribut lang ou xml:lang qui décrit la langue du document.",
        "3_1_1_H57.3.Lang": "La langue spécifiée dans l'attribut lang de l'élément de document ne semble pas être bien formée.",
        "3_1_1_H57.3.XmlLang": "La langue spécifiée dans l'attribut xml:lang de l'élément document ne semble pas être bien formée.",
        "3_1_2_H58": "Assurez-vous que tout changement de langue est marqué à l'aide de l'attribut lang et/ou xml:lang sur un élément, selon le cas.",
        "3_1_2_H58.1.Lang": "La langue spécifiée dans l'attribut lang de cet élément ne semble pas être bien formée.",
        "3_1_2_H58.1.XmlLang": "Le langage spécifié dans l'attribut xml:lang de cet élément ne semble pas être bien formé.",
        "3_1_3_H40,H54,H60,G62,G70": "Vérifier qu'il existe un mécanisme permettant d'identifier des définitions spécifiques de mots ou de phrases utilisés d'une manière inhabituelle ou restreinte, y compris les expressions idiomatiques et le jargon.",
        "3_1_4_G102,G55,G62,H28,G97": "Vérifier qu'il existe un mécanisme permettant d'identifier la forme élargie ou la signification des abréviations.",
        "3_1_5_G86,G103,G79,G153,G160": "Lorsque le contenu exige une capacité de lecture plus avancée que le niveau de l'enseignement secondaire inférieur, un contenu supplémentaire ou une version alternative devrait être fourni.",
        "3_1_6_H62.1.HTML5": "Ruby element does not contain an rt element containing prononciation information for its body text.",
        "3_1_6_H62.1.XHTML11": "Ruby element does not contain an rt element containing prononciation information for the text inside the rb element.",
        "3_1_6_H62.2": "Ruby element does not contain rp elements, which provide extra punctuation to browsers not supporting ruby text.",
        "3_2_1_G107": "Vérifier qu'il n'y a pas de changement de contexte lorsque ce champ de saisie reçoit le focus.",
        "3_2_2_H32.2": 'Ce formulaire ne contient pas de bouton de soumission, ce qui crée des problèmes pour ceux qui ne peuvent pas soumettre le formulaire à l\'aide du clavier. Les boutons Submit sont des éléments INPUT avec l\'attribut de type "submit" ou "image", ou des éléments BUTTON avec le type "submit" ou omis/invalid.',
        "3_2_3_G61": "Vérifiez que les mécanismes de navigation qui sont répétés sur plusieurs pages Web se produisent dans le même ordre relatif chaque fois qu'ils sont répétés, à moins qu'un changement ne soit initié par l'utilisateur.",
        "3_2_4_G197": "Vérifier que les composants qui ont la même fonctionnalité dans cette page Web sont identifiés de manière cohérente dans l'ensemble des pages Web auxquelles ils appartiennent.",
        "3_2_5_H83.3": "Vérifiez que le texte du lien de ce lien contient des informations indiquant que le lien s'ouvrira dans une nouvelle fenêtre.",
        "3_3_1_G83,G84,G85": "Si une erreur de saisie est automatiquement détectée dans ce formulaire, vérifiez que le ou les éléments erronés sont identifiés et que l'erreur ou les erreurs sont décrites à l'utilisateur sous forme de texte.",
        "3_3_2_G131,G89,G184,H90": "Vérifier que les étiquettes descriptives ou les instructions (y compris pour les champs obligatoires) sont fournies pour l'entrée de l'utilisateur dans ce formulaire.",
        "3_3_3_G177": "Vérifier que ce formulaire fournit les corrections suggérées en cas d'erreurs dans les entrées des utilisateurs, à moins que cela ne compromette la sécurité ou l'objectif du contenu.",
        "3_3_4_G98,G99,G155,G164,G168.LegalForms": "Si ce formulaire lie un utilisateur à un engagement financier ou juridique, modifie/supprime des données contrôlables par l'utilisateur, ou soumet des réponses de test, assurez-vous que les soumissions sont réversibles, vérifiées pour les erreurs de saisie et/ou confirmées par l'utilisateur.",
        "3_3_5_G71,G184,G193": "Vérifiez que l'aide contextuelle est disponible pour ce formulaire, au niveau de la page Web et/ou du contrôle.",
        "3_3_6_G98,G99,G155,G164,G168.AllForms": "Vérifier que les soumissions à ce formulaire sont soit réversibles, soit vérifiées pour les erreurs de saisie, et/ou confirmées par l'utilisateur.",
        "4_1_1_F77": 'Dupliquer la valeur de l\'attribut id "{{id}}" trouvée sur la page web.',
        "4_1_2_H91.A.Empty": "L'élément d'ancrage trouvé avec un ID mais sans href ou texte de lien. Envisager de déplacer son ID vers un élément parent ou un élément voisin.",
        "4_1_2_H91.A.EmptyWithName": "L'élément d'ancrage trouvé avec un attribut de nom mais sans href ou texte de lien. Envisagez de déplacer l'attribut de nom pour qu'il devienne l'ID d'un parent ou d'un élément voisin.",
        "4_1_2_H91.A.EmptyNoId": "Élément d'ancrage trouvé sans contenu de lien et sans nom et/ou attribut ID.",
        "4_1_2_H91.A.NoHref": "Les éléments d'ancrage ne doivent pas être utilisés pour définir des cibles de liens en page. Si vous n'utilisez pas l'ID à d'autres fins (comme le CSS ou le script), envisagez de le déplacer vers un élément parent",
        "4_1_2_H91.A.Placeholder": "L'élément d'ancrage trouvé avec le contenu du lien, mais aucun attribut href, ID ou nom n'a été fourni.",
        "4_1_2_H91.A.NoContent": "L'élément d'ancrage trouvé avec un attribut href valide, mais aucun contenu de lien n'a été fourni.",
        "4_1_2_input_element": "élément d'entrée",
        "4_1_2_role_of_button": 'l\'élément a un rôle de "bouton" mais',
        "4_1_2_element_content": "contenu de l'élément",
        "4_1_2_element": "élément",
        "4_1_2_msg_pattern": "Ce {{msgNodeType}} n'a pas de nom disponible pour une API d'accessibilité. Les noms valides le sont : {{builtAttrs}}.",
        "4_1_2_msg_pattern2": "Cette {{{msgNodeType}} n'a pas de valeur disponible pour une API d'accessibilité.",
        "4_1_2_msg_add_one": "Ajouter un en ajoutant du contenu à l'élément.",
        "4_1_2_msg_pattern3": "Cette {{msgNodeType}} n'a pas d'option initialement sélectionnée. Selon votre version HTML, la valeur exposée à une API d'accessibilité peut être indéfinie.",
        "4_1_2_value_exposed_using_attribute": "Une valeur est exposée à l'aide de l'attribut {{requiredValue}}.",
        "4_1_2_value_exposed_using_element": "Une valeur est exposée à l'aide de l'élément {{requiredValue}}."
    }, _global.translation.pl = {
        auditor_name: "Squiz HTML_CodeSniffer",
        auditor_using_standard: "Używany standard",
        auditor_standards: "Standardy",
        auditor_code_snippet: "Fragment kodu",
        auditor_close: "Zamknij",
        auditor_select_types: "Wybierz typy błędów, które mają być w raporcie",
        auditor_home: "Home",
        auditor_view_report: "Zobacz Raport",
        auditor_report: "Raport",
        auditor_back_to_report: "Powrót do Raportu",
        auditor_previous_issue: "Poprzedni Problem",
        auditor_next_issue: "Następny Problem",
        auditor_issue: "Problem",
        auditor_of: "z",
        auditor_errors: "Błędy",
        auditor_error: "Błąd",
        auditor_warnings: "Ostrzeżenia",
        auditor_warning: "Ostrzeżenie",
        auditor_notices: "Uwagi",
        auditor_notice: "Uwaga",
        auditor_toggle_display_of: "Przełącz wyświetlanie",
        auditor_messages: "komunikatów",
        auditor_unable_to_point: "Nie można wskazać elementu powiązanego z tym kryterium.",
        auditor_unable_to_point_entire: "Nie można wskazać tego problemu, ponieważ dotyczy on całego dokumentu.",
        auditor_unable_to_point_removed: "Nie można wskazać tego elementu, ponieważ został on usunięty z dokumentu od momentu wygenerowania raportu.",
        auditor_unable_to_point_outside: "Nie można wskazać tego elementu, ponieważ znajduje się poza elementem treści dokumentu.",
        auditor_unable_to_point_hidden: "Nie można wskazać tego elementu, ponieważ jest on niewidoczny lub nie ma reprezentacji wizualnej.",
        auditor_point_to_element: "Pokaż element",
        auditor_unsupported_browser: "Funkcja nie jest obsługiwana w tej przeglądarce.",
        auditor_page: "Strona",
        auditor_updated_to: "HTML_CodeSniffer został zaktualizowany do wersji",
        auditor_view_the_changelog: "Zobacz dziennik zmian",
        auditor_success_criterion: "Kryteria sukcesu",
        auditor_suggested_techniques: "Sugerowane techniki",
        auditor_applies_entire_document: "Dotyczy to całego dokumentu",
        "1_1_1_H30.2": "Grafika jest jedyną treścią linku i powinna zostać uzupełniona o opis alternatywny opisujący jego funkcję.",
        "1_1_1_H67.1": "Grafika pełni funkcję dekoracyjną zarówno atrybut alt jak i title powinny być puste.",
        "1_1_1_H67.2": "Grafika jest oznaczona jako dekoracyjna i będzie ignorowana przez technologie wspomagające np. czytniki ekranu dla osób niewidomych.",
        "1_1_1_H37": "Znacznik <img> nie ma atrybutu alt. Dodaj ten atrybut i wpisz do niego krótki opis grafiki.",
        "1_1_1_G94.Image": "Upewnij się, że opis alternatywny grafiki przekazuje tę samą informację, co sama grafika.",
        "1_1_1_H36": "Przycisk graficzny nie ma atrybutu alt. Dodaj do przycisku atrybut alt i opisz w nim funkcję przycisku.",
        "1_1_1_G94.Button": "Upewnij się, że opis alternatywny przycisku prawidłowo opisuje funkcję przycisku.",
        "1_1_1_H24": "Element <area> w mapie obrazkowej nie ma atrybutu alt. Każdy taki obszar powinien mieć atrybut alt z opisem alternatywnym, który odpowiednio opisuje dany obszar.",
        "1_1_1_H24.2": "Upewnij się, że opis alternatywny obszaru <area> odpowiada jego funkcji lub treści.",
        "1_1_1_G73,G74": "Jeśli krótki opis alternatywny nie opisuje wystarczająco treści prezentowanej przez grafikę, dodaj rozszerzony opis bezpośrednio na stronie lub na oddzielnej stronie.",
        "1_1_1_H2.EG5": "Opis alternatywny elementu <img> umieszczonego wewnątrz linku, nie może być taki sam jak tekst samego linku.",
        "1_1_1_H2.EG4": "Element <img> znajdujący się wewnątrz linku nie ma atrybutu alt lub alt jest pusty. Ponieważ w sąsiedztwie znajduje się link tekstowy zaleca się połączenie tych elementów w jedno łącze ze wspólnym opisem.",
        "1_1_1_H2.EG3": "Element <img> znajdujący się wewnątrz linku nie może mieć opisu alternatywnego tej samej treści, co link tekstowy znajdujący się w jego sąsiedztwie.",
        "1_1_1_H53,ARIA6": "Element <object> musi zawierać atrybut alt z wprowadzonym opisem alternatywnym o ile nie została zapewniony żaden inny alternatywny opis tego elementu.",
        "1_1_1_G94,G92.Object,ARIA6": "Należy sprawdzić czy krótkie lub długie teksty alternatywne są dostępne dla wszystkich elementów nietekstowych i możliwie najdokładniej je opisują.",
        "1_1_1_H35.3": "<applet> powinien zawierać opis alternatywny wewnątrz znaczników <object>. Zapewni to alternatywę w przeglądarkach, które nie obsługują elementu <applet>.",
        "1_1_1_H35.2": "Applet powinien mieć opis alternatywny w atrybucie alt, aby zapewnić wsparcie w przypadku problemów z załadowaniem zawartości.",
        "1_1_1_G94,G92.Applet": "Należy sprawdzić czy krótkie lub długie teksty alternatywne są dostępne dla wszystkich elementów nietekstowych i możliwie najdokładniej je opisują.",
        "1_2_1_G158": "Treść przedstawiona w sposób dźwiękowy powinna zostać uzupełniona dodatkową transkrypcją lub opisem alternatywnym.",
        "1_2_1_G159,G166": "Jeśli obiekt wideo (bez dźwięku) zamieszczony na stronie nie jest alternatywą dla tekstu, sprawdź czy istnieje do niego alternatywa tekstowa lub ścieżka dźwiękowa prezentująca tę samą treść.",
        "1_2_2_G87,G93": "Jeśli obiekt wideo ze ścieżką audio nie jest alternatywą dla tekstu, sprawdź czy materiał ma napisy dla niesłyszących.",
        "1_2_3_G69,G78,G173,G8": "Jeśli obiekt wideo ze ścieżką audio nie jest alternatywą dla tekstu, sprawdź czy materiał zawiera dodatkowo audiodeskrypcję obrazu i/lub opis alternatywny dla treści przedstawionych wyłącznie w formie obrazu.",
        "1_2_4_G9,G87,G93": "Sprawdź czy materiał wideo prezentowany na żywo ma napisy dla niesłyszących tworzone na żywo.",
        "1_2_5_G78,G173,G8": "Sprawdź czy materiał wideo ma dołączoną audiodeskrypcję obrazu.",
        "1_2_6_G54,G81": "Sprawdź czy materiał wideo ma dołączone tłumaczenie na język migowy.",
        "1_2_7_G8": "Jeśli materiał wideo, nie ma wystarczających pauz, by uzupełnić go o audiodeskrypcję, sprawdź czy zamieszczona jest alternatywna wersja lub rozszerzona ścieżka dźwiękowa.",
        "1_2_8_G69,G159": "Sprawdź czy materiał wideo lub wideo (sam obraz) jest umieszczony wraz z alternatywą tekstową.",
        "1_2_9_G150,G151,G157": "Sprawdź czy material audio przekazywany na żywo jest umieszczony wraz z wersją tekstową tworzoną na żywo.",
        "1_3_1_F92,ARIA4": 'Ten element ma przypisaną rolę "prezentacja" ale zawiera też konkretne treści.',
        "1_3_1_H44.NonExistent": 'Atrybut "for" znacznika <label> zawiera identyfikator pola, które nie istnieje na stronie.',
        "1_3_1_H44.NonExistentFragment": 'Atrybut "for" znacznika <label> zawiera identyfikator pola, które nie istnieje w tym obszarze strony.',
        "1_3_1_H44.NotFormControl": 'Atrybut "for" znacznika <label> zawiera identyfikator, który jest przypisany do elementu nie będącego elementem formulrza. Sprawdź czy identyfikator został umieszczony w odpowiednim znaczniku.',
        "1_3_1_H65": 'Element formularza zawiera atrybut "title" bez treści lub z samymi spacjami / odstępami. Taki title nie będzie uwzględniany jako prawidłowa etykieta.',
        "1_3_1_ARIA6": 'To pole formularza ma atrybut "aria-label" bez treści lub z samymi spacjami / odstępami. Taki atrybut nie będzie uwzględniany jako prawidłowa etykieta.',
        "1_3_1_ARIA16,ARIA9": 'To pole formularza ma atrybut "aria-labelledby. Jednak jego ID "{{id}}" nie istnieje w żadnym elemencie. Taki atrybut nie będzie uwzględniany jako opis pola.',
        "1_3_1_F68.Hidden": "To ukryte pole ma etykietę (opis). Nie ma potrzeby dodawania etykiety do ukrytego pola.",
        "1_3_1_F68.HiddenAttr": 'To pole ma z zasady być ukryte (za pomocą atrybutu "hidden"). Nie ma potrzeby dodawania etykiety do ukrytego pola.',
        "1_3_1_F68": 'Pole formularza powinno posiadać opis bądź etykietę. Zaleca się dodanie atrybutów "title", "aria-label" lub "aria-labelledby" bądź  elementu label (z atrybutem "for" wskajuącym na to pole) .',
        "1_3_1_H49.": "Znaczniki prezentacyjne użyte w kodzie są przestarzałe w HTML5.",
        "1_3_1_H49.AlignAttr": "Align attributes.",
        "1_3_1_H49.Semantic": "Do wyróżnienia tekstu powinny zostać użyte odpowiednie znaczniki.",
        "1_3_1_H49.AlignAttr.Semantic": "Do wyróżnienia tekstu powinny zostać użyte odpowiednie znaczniki.",
        "1_3_1_H42": "Jeśli ten fragment treści miał być nagłówkiem, powinny zostać użyte znaczniki nagłówków (h1 – h6).",
        "1_3_1_H63.3": 'W komórce tabeli użyto niewłaściwy atrybut "scope". Dopuszczalne atrybuty to: "ow", "col", "rowgroup" i "colgroup".',
        "1_3_1_H63.2": "Atrybuty scope używane na komórkach tabeli (<td>) aby oznaczyć nagłówek są przestarzałe w HTML5. Zaleca się skorzystanie ze znacznika <th>.",
        "1_3_1_H43.ScopeAmbiguous": 'Użycie atrybutów scope na elementach th jest niejasne w tabeli z wieloma nagłówkami. Rekomenduje się użycie atrybutu "headers" w znaczniku <td>.',
        "1_3_1_H43.IncorrectAttr": 'Nieprawidłowy atrybut nagłówka w znaczniku <td>. Zamiast "{{actual}}" powinien być "{{expected}}".',
        "1_3_1_H43.HeadersRequired": 'Nie zdefiniowano relacji pomiędzy komórką <td> a powiązanym z nią nagłówkiem <th>. Wskazana tabela ma wiele poziomów <th>, zatem należałoby użyć atrybutu "headers" w komórkach <td>.',
        "1_3_1_H43.MissingHeaderIds": 'Nie wszystkie nagłówki th w tej tabeli posiadają atrybut "id" i nie będzie się można do nich odwołać za pomocą atrybutu "headers" w znacznikach <td>.',
        "1_3_1_H43.MissingHeadersAttrs": "Nie wszystkie komórki <td> w tej tabeli posiadają atrybut headers. W atrybucie headers powinny znajdować się identyfikatory powiązanych nagłówków.",
        "1_3_1_H43,H63": 'Komórki <td> nie mają ustalonej relacji z komórkami nagłówkowymi <th>. Należy zdefiniować atryb "scope" dla <th> lub "headers" dla <td>.',
        "1_3_1_H63.1": 'Nie wszystkie komórki <th> w tej tabeli posiadają atrybut "scope". Ten atrybut pozwala powiązać nagłówki <th> z odpowiednimi komórkami <td>.',
        "1_3_1_H73.3.LayoutTable": "Tabela jest prawdopodobnie używana do struktury układu graficznego ale zawiera atrybut summary. Tabele używane do budowania struktury strony nie mieć tego atrybutu.",
        "1_3_1_H39,H73.4": 'Jeśli tabela zawiera treści i zastosowano w niej atrybut "summary" i znacznik <caption>. Treści tego atrybutu i znacznika nie mogą być powielone.',
        "1_3_1_H73.3.Check": 'Jeśli ta tabela zawiera dane, sprawdź czy atrybut "summary" opisuje układ tabeli oraz wyjaśnia jak jej użyć.',
        "1_3_1_H73.3.NoSummary": 'Jeśli ta tabela zawiera dane, rozważ użycie atrybutu "summary" jako podsumowania zawartości tabeli.',
        "1_3_1_H39.3.LayoutTable": "Ta tabela wydaje się być przeznaczona do prezentacji układu strony, ale zawiera opis <caption>. Tabele przeznaczone do układu nie mogą zawierać opisu.",
        "1_3_1_H39.3.Check": "Jeśli ta tabela zawiera dane, sprawdź czy <caption> zawiera jej adekwatny opis.",
        "1_3_1_H39.3.NoCaption": "Jeśli ta tabela zawiera dane, powinna być uzupełniona o opis w znaczniku <caption>.",
        "1_3_1_H71.NoLegend": "Fieldset nie ma opisu w formie <legend>. Wszystkim znacznikom <fieldset> powinny towarzyszyć opisy w znaczniku <legend>.",
        "1_3_1_H85.2": "Jeśli listy wyboru stanowią grupę, powinny być zgrupowane elementem <optgroup>.",
        "1_3_1_H71.SameName": 'Jeśli te pola "checkbox" i "radio" są powiązane, powinny być zgrupowane znacznikiem <fieldset>.',
        "1_3_1_H48.1": "Treść przypomina listę nieuporządkowaną. Jeśli jest to lista nieuporządkowana powinna być oznaczona odpowiednimi znacznikami <ul>.",
        "1_3_1_H48.2": "Treść przypomina listę uporządkowaną. Jeśli jest to lista uporządkowana powinna być oznaczona odpowiednimi znacznikami <ol>.",
        "1_3_1_G141_a": "Kolejność nagłówków nie jest poprawnie uporządkowana. Element h{{headingNum}} umieszczony jest jako pierwszy więc powinien być oznaczony jako h1.",
        "1_3_1_G141_b": "Kolejność nagłówków nie jest poprawnie uporządkowana. Nagłówek h{{headingNum}} powinien być oznaczony jako h{{properHeadingNum}}.",
        "1_3_1_H42.2": "Nagłówek nie posiada tekstu. Tekst, który nie ma funkcji nagłówka nie powinien być oznaczony znacznikami do określania nagłówków.",
        "1_3_1_H48": "Jeśli ten element zawiera nawigację, zaleca się aby został oznaczony jako lista np. przy użyciu listy nieuporządkowanej <ul>.",
        "1_3_1_LayoutTable": "Ta tabela wydaje się być przeznaczona do prezentacji układu strony. Jeśli jest inaczej, powinna zawierać nagłówki pod postacią <th>.",
        "1_3_1_DataTable": 'Jeżeli tabela jest wykorzystywana do tworzenia układu strony, nie powinna posiadać komórek <th> oraz atrybutu "summary" ani znacznika z opisem <caption>.',
        "1_3_2_G57": "Struktura i ułożenie elementów powinny oddawać sens strony i informacji.",
        "1_3_3_G96": "Sprawdź czy przekazane instrukcje nie opierają się tylko na właściwościach postrzegalnych przez zmysły takich jak kształt, wielkość czy umiejscowienie.",
        "1_4_1_G14,G18": "Informacje przekazywane za pomocą kolorów powinny być również zapisywane w treści lub mieć inną postać alternatywną.",
        "1_4_2_F23": "Jeżeli materiał audo włączany jest automatycznie i trwa dłużej niż trzy sekundy należy umożliwić zatrzymanie bądź wyciszenie odtwarzania.",
        "1_4_3_F24.BGColour": "Sprawdź czy kolor tekstu odpowiada pod względem wymagań kontrastu kolorowi lub obrazkowi tła.",
        "1_4_3_F24.FGColour": "Sprawdź czy kolor elementu ma taki kolor tła lub w formie obrazka, który pasuje kontrastowo do elementu inline z tekstem.",
        "1_4_3_G18_or_G145.Abs": "Ten element jest pozycjonowany absolutnie i nie jest możliwe zweryfikowanie jego kontrastu. Sprawdź samodzielnie czy kontrast jest na wystarczającym poziomie: {{required}}:1.",
        "1_4_3_G18_or_G145.BgImage": "Tekst elementu wyświetlany jest na obrazku. Należy upewnić się, że stosunek kontrastu tekstu do tła wynosi co najmniej {{required}}:1.",
        "1_4_3_G18_or_G145.Alpha": "Tekst lub tło tego elementu jest przezroczyste. Upewnij się, że współczynnik kontrastu między tekstem a tłem wynosi co najmniej {{required}}:1.",
        "1_4_3_G18_or_G145.Fail": "Ten element ma niewystarczający na tym poziomie zgodności stosunek kontrastu tekstu do tła. Powinien wynosić co najmniej {{required}}:1, a tekst umieszczony w tym elemencie posiada stosunek {{value}}:1.",
        "1_4_3_G18_or_G145.Fail.Recomendation": "Zalecenie: zmiana",
        "1_4_3_G18_or_G145.Fail.Recomendation.Text": "koloru tekstu na",
        "1_4_3_G18_or_G145.Fail.Recomendation.Background": "koloru tła na",
        "1_4_4_G142": "Należy upewnić się, że tekst na stronie może być powiększony do 200% bez użycia technologii wspomagających.",
        "1_4_5_G140,C22,C30.AALevel": "Sprawdź czy stosowane technologie pozwalają na użycie tekstu w graficznej formie. Treść powinna być przekazana w formie tekstu, chyba że graficzna forma tekstu jest istotna z punktu widzenia samej informacji, która jest przekazywana.",
        "1_4_6_G18_or_G17.Abs": "Ten element jest pozycjonowany absolutnie i nie jest możliwe zweryfikowanie jego kontrastu. Sprawdź samodzielnie czy kontrast jest na wystarczającym poziomie: {{required}}:1.",
        "1_4_6_G18_or_G17.BgImage": "Tekst elementu wyświetlany jest na obrazku. Należy upewnić się, że stosunek kontrastu tekstu do tła wynosi conajmniej {{required}}:1.",
        "1_4_6_G18_or_G17.Fail": "Ten element ma niewystarczający stosunek kontrastu tekstu do tła. Powinien wynosić co najmniej {{required}}:1, a tekst umieszczony w tym elemencie posiada stosunek {{value}}:1.",
        "1_4_6_G18_or_G17.Fail.Recomendation": "Zalecenie: zmiana",
        "1_4_6_G18_or_G17.Fail.Recomendation.Text": "koloru tekstu na",
        "1_4_6_G18_or_G17.Fail.Recomendation.Background": "koloru tła na",
        "1_4_7_G56": "Materiał audio, w którym występuje głównie mowa powinien mieć możliwość wyciszenia dźwięków tła lub narracja powinna być przynajmniej o 4 razy(20dB) głośniejsza niż tło.",
        "1_4_8_G148,G156,G175": "Kolor tekstu oraz kolor tła powinny być możliwe do zmiany przez użytkownika na samej stronie lub poprzez funkcje przeglądarki. ",
        "1_4_8_H87,C20": "Szerokość bloku tekstowego nie powinna przekraczać 80 znaków (40 w przypadku alfabetów: chińskiego, japońskiego i koreańskiego).",
        "1_4_8_C19,G172,G169": "Tekst nie powinien być wyjustowany (tzn. wyrównany do prawego i lewego marginesu). Ewentualnie powinien istnieć mechanizm, który pozwala usunąć wyjustowanie.",
        "1_4_8_G188,C21": "Odstępy między wierszami w akapitach powinny wynosić  przynajmniej 1,5 wysokości linii, a odległość między akapitami powinna być przynajmniej 1,5 razy większa niż ta pomiędzy wierszami. ",
        "1_4_8_H87,G146,C26": "Tekst na stronie powinien mieć możliwość powiększenia do 200% bez użycia technologii wspomagających. Nie powinno być też wymagane scrollowanie dolnym paskiem ekranu przy takim ustawieniu i zmaksymalizowanym oknie przeglądarki.",
        "1_4_9_G140,C22,C30.NoException": "Sprawdź czy teksty w formie grafiki pełnią wyłącznie funkcję dekoracyjną lub „graficzna” prezentacja tekstu ma znaczenie z uwagi na treść przekazywanej informacji.",
        "2_1_1_G90": "Cała treść oraz wszystkie zawarte w niej funkcjonalności powinny być dostępne przy użyciu klawiatury.",
        "2_1_1_SCR20.DblClick": "Funkcjonalność dostępna po dwukrotnym kliknięciu przyciskiem myszy, również powinna być dostępna przy użyciu klawiatury.",
        "2_1_1_SCR20.MouseOver": "Funkcjonalność dostępna po najechaniu kursorem myszy na element, również powinna być dostępna przy użyciu klawiatury.",
        "2_1_1_SCR20.MouseOut": "Funkcjonalność dostępna po opuszczeniu kursorem myszy elementu, również powinna być dostępna przy użyciu klawiatury.",
        "2_1_1_SCR20.MouseMove": "Funkcjonalność dostępna z wykorzystaniem ruchów myszą, również powinna być dostępna przy użyciu klawiatury.",
        "2_1_1_SCR20.MouseDown": "Funkcjonalność dostępna po wciśnięciu przycisku myszy, również powinna być dostępna przy użyciu klawiatury.",
        "2_1_1_SCR20.MouseUp": "Funkcjonalność dostępna po zwolnieniu przycisku myszy, również powinna być dostępna przy użyciu klawiatury.",
        "2_1_2_F10": "Sprawdź czy plugin lub applet pozwalają na uwolnienie fokusu, jeśli przejdzie on do tych funkcjonalności.",
        "2_2_1_F40.2": 'Tag <meta> z atrybutem "redirect" przekierowuje na inną stronę w czesie określonym jako 0. Użytkownicy nie mają kontroli w taki krótkim czasie.',
        "2_2_1_F41.2": 'Tag <meta> z atrybutem "refresh" odświeża aktualną stronę. Użytkownicy nie mają kontroli nad tym odświeżaniem.',
        "2_2_2_SCR33,SCR22,G187,G152,G186,G191": "Jeśli na stronie znajduje się element dynamiczny, animujący się dłużej niż 5 sekund powinien być mechanizm umożliwiający zatrzymanie tego efektu.",
        "2_2_2_F4": "Jeśli element miga dłużej niż 5 sekund powinien być mechanizm, który umożliwi zatrzymanie migania.",
        "2_2_2_F47": "Element <blink> nie spełnia wymagania o zatrzymaniu się migania w czasie 5 sekund.",
        "2_2_3_G5": "Sprawdź czy w serwisie nie występują ograniczenia czasowe, w działaniach użytkownika, chyba że dotyczy to odtwarzania plików multimedialnych lub działań transmitowanych na żywo.",
        "2_2_4_SCR14": "Sprawdź czy wszelkie przerwy lub przeszkody (np. automatyczna aktualizacja treści), mogą być wyłączone lub opóźnione przez użytkownika — chyba, że dotyczy to nagłych przypadków.",
        "2_2_5_G105,G181": "Jeśli użytkownik zostanie wylogowany w trakcie procesu (np. krokowego), powinien mieć możliwość do powrotu do tego samego stanu procesu, w jakim był wcześniej.",
        "2_3_1_G19,G176": "Elementy strony nie mogą błyskać częściej niż trzy razy w ciągu sekundy oraz jednocześnie obszar migający jest jak najmniejszy.",
        "2_3_2_G19": "Elementy strony nie mogą migać częściej niż trzy razy w ciągu sekundy.",
        "2_4_1_H64.1": 'Ramka <iframe> musi posiadać tytuł. Należy wypełnić atrybut "title" i zawrzeć w nim opis ramki.',
        "2_4_1_H64.2": "Tytuł ramki <iframe> powinień opisywać ją w możliwie najdokładniejszy sposób.",
        "2_4_1_G1,G123,G124,H69": "Sprawdź czy użytkownik może pominąć powtarzającą się nawigację. Można to zapewnić przez użycie skip-linków, nagłówków lub punktów orientacyjnych ARIA.",
        "2_4_1_G1,G123,G124.NoSuchID": "Ten link wskazuje identyfikator {{id}} ale takiego identyfikatora w dokumencie nie ma.",
        "2_4_1_G1,G123,G124.NoSuchIDFragment": "Ten link wskazuje identyfikator {{id}} w dokumencie, ale takiego identyfikatora w testowanym obszarze nie ma.",
        "2_4_2_H25.1.NoHeadEl": "Strona nie ma tytułu. Należy dodać znacznik <title> do sekcji nagłówkowej strony.",
        "2_4_2_H25.1.NoTitleEl": "Strona nie ma tytułu. Należy dodać znacznik <title> do sekcji nagłówkowej strony lub wypełnić jego treść.",
        "2_4_2_H25.1.EmptyTitle": "Znacznik <title> w sekcji nagłówkowej strony nie powinien być pusty.",
        "2_4_2_H25.2": "Znacznik <title> w sekcji nagłówkowej strony powinien możliwie najdokładniej opisywać jej zawartość.",
        "2_4_3_H4.2": 'Jeśli został użyty atrybut "tabindex", relacje i następstwo treści są zrozumiałe dla użytkownika.',
        "2_4_4_H77,H78,H79,H80,H81,H33": 'Sprawdź czy treść linku wraz z kontekstem, w którym występuje lub dodatkową treścią w atrybucie "title" pozwala na zrozumienie funkcji / celu linku.',
        "2_4_4_H77,H78,H79,H80,H81": "Sprawdź czy treść linku wraz z kontekstem, w którym występuje pozwala na zrozumienie funkcji / celu linku.",
        "2_4_5_G125,G64,G63,G161,G126,G185": "Jeśli strona nie jest częścią procesu krokowego, sprawdź czy jest więcej niż jeden sposób na dotarcie do tej strony.",
        "2_4_6_G130,G131": "Nagłówki i etykiety powinny opisywać temat i/lub cel treści.",
        "2_4_7_G149,G165,G195,C15,SCR31": "Każdy element możliwy do nawigacji za pomocą klawiatury, ma widoczny wskaźnik fokusu klawiatury.",
        "2_4_8_H59.1": "Znacznik <link> może być umieszczony wyłącznie w sekcji <head>.",
        "2_4_8_H59.2a": 'Znacznik <link> nie ma atrybutu "rel", którego wartość wskazuje na typ i powiązanie linku.',
        "2_4_8_H59.2b": 'Znacznik <link> nie ma adresu w atrybucie "href" — nie prowadzi do żadnego zasobu.',
        "2_4_9_H30": "Sprawdź czy treść linku zrozumiale opisuje jego funkcję.",
        "3_1_1_H57.2": 'Znacznik <html> musi posiadać atrybut "lang" określający język strony.',
        "3_1_1_H57.3.Lang": 'Język zdefiniowany w atrybucie "lang" nie mógł zostać poprawnie zinterpretowany.',
        "3_1_1_H57.3.XmlLang": 'Język zdefiniowany w atrybucie "xml:lang" nie mógł zostać poprawnie zinterpretowany. ',
        "3_1_2_H58": 'Każda zmiana języka w treściach strony powinna być oznaczona atrybutem "lang" lub "xml:lang".',
        "3_1_2_H58.1.Lang": 'Język zdefiniowany w atrybucie "lang" tego elementu nie mógł zostać poprawnie zinterpretowany.',
        "3_1_2_H58.1.XmlLang": 'Język zdefiniowany w atrybucie "xml:lang" tego elementu nie mógł zostać poprawnie zinterpretowany.',
        "3_1_3_H40,H54,H60,G62,G70": "Sprawdź czy jest zapewniony mechanizm, za pomocą którego można sprawdzić definicje słów użytych w nietypowy sposób, co odnosi się też do idiomów i żargonu.",
        "3_1_4_G102,G55,G62,H28,G97": "Sprawdź czy jest zapewniony mechanizm, za pomocą którego można sprawdzić znaczenie skrótów w ich rozwiniętej formie.",
        "3_1_5_G86,G103,G79,G153,G160": "Jeśli dany tekst wymaga umiejętności czytania na poziomie wyższym niż poziom gimnazjalny, powinna być dostępna jego dodatkowa, uproszczona wersja.",
        "3_1_6_H62.1.HTML5": "Element <ruby> nie zawiera znacznika znacznika <rt>, który opisuje sposób wymowy.",
        "3_1_6_H62.1.XHTML11": "Element <ruby> nie zawiera znacznika <rt>, który opisuje sposób wymowy treści umieszczonej wewnątrz znacznika <rb>.",
        "3_1_6_H62.2": "Element <ruby> nie zawiera znacznika <rp>, który zapewnia wsparcie dla przeglądarek niewspierających treści w znacznikach <ruby>.",
        "3_2_1_G107": "Sprawdź czy po przenieseniu fokusu na dany element formularza nie następuje zaskakująca dla użytkownika zmiana kontekstu (np. przeniesienie na inną stronę).",
        "3_2_2_H32.2": 'Formularz nie ma przycisku do zatwierdzania wysyłki. Może to sprawić problem użytkownikom posługującym się wyłącznie klawiaturą. Przycisk może mieć formę elementu <input> z atrybutem "submit" lub "image" lub elementem <button>.',
        "3_2_3_G61": "Sprawdź czy powtarzające się na stronach elementy nawigacyjne są umieszczone w tej samej kolejności. Dopuszczalne jest zmiana kolejności przez samego użytkownika.",
        "3_2_4_G197": "Elementy, które posiadają tę samą funkcjonalność, muszą być zidentyfikowane/oznaczone w ten sam sposób na wszystkich stronach serwisu.",
        "3_2_5_H83.3": "Linki otwierające się w nowym oknie lub karcie przeglądarki powinny mieć taką informację w swojej treści.",
        "3_3_1_G83,G84,G85": "Jeśli przy wpisywaniu informacji, błąd zostanie wykryty automatycznie, system powinien wskazać błędny element, a użytkownik otrzymać opis błędu w postaci tekstu.",
        "3_3_2_G131,G89,G184,H90": "Jeżeli wymagane jest wprowadzenie treści przez użytkownika, powinny być dostępne zrozumiałe instrukcje i wskazówki.",
        "3_3_3_G177": "Jeśli przy wpisywaniu informacji błąd zostanie wykryty automatycznie, użytkownik powinien otrzymać sugestię korekty, chyba że wpłynie to negatywnie na bezpieczeństwo systemu.",
        "3_3_4_G98,G99,G155,G164,G168.LegalForms": "Sprawdź czy użytkownik może sprawdzić i zmienić informacje wprowadzone w formularzu, zanim ostatecznie potwierdzi jego wysłanie. Jest to szczególnie ważne w sytuacji, gdy użytkownik podaje informacje prawne lub finansowe.",
        "3_3_5_G71,G184,G193": "Sprawdź czy w formularzu jest dostępna pomoc kontekstowa na samej stronie lub na stronie służącej do potwierdzenia wprowadzonych treści.",
        "3_3_6_G98,G99,G155,G164,G168.AllForms": "Sprawdź czy użytkownik może sprawdzić i zmienić informacje wprowadzone w formularzu, zanim ostatecznie potwierdzi jego wysłanie.",
        "4_1_1_F77": 'Na stronie znaleziono powieloną wartość "{{id}}". Dopuszczalne jest jedno wystąpienie wartości identyfikatora.',
        "4_1_2_H91.A.Empty": 'Link ma atrybut "id", nie ma jednak ani adresu w atrybucie "href" ani treści. Rozważ przeniesienie identyfikatora do elementu nadrzędnego.',
        "4_1_2_H91.A.EmptyWithName": 'Link ma atrybut "name", ale nie ma ani adresu ani treści. Rozważ przeniesienie wartości atrybutu "name" do identyfikatora elementu nadrzędnego jako "id".',
        "4_1_2_H91.A.EmptyNoId": "Link nie ma treści, ani nazwy ani identyfikatora.",
        "4_1_2_H91.A.NoHref": "Link nie powinien być używany do tworzenia wewnętrznych odniesień na stronie. Jeśli identyfikator jest używany do styli CSS lub oskryptowania JS, rozważ przeniesienie identyfikatora od elementu nadrzędnego (rodzica).",
        "4_1_2_H91.A.Placeholder": 'Link ma jedynie treść. Brakuje adresu w atrybucie "href", identyfikatora ani nazwy (atrybutu "name").',
        "4_1_2_H91.A.NoContent": 'Link ma prawidłowy adres w atrybucie "href" ale nie ma treści linku.',
        "4_1_2_input_element": "pole formularza",
        "4_1_2_role_of_button": 'element ma rolę "button" ale',
        "4_1_2_element_content": "zawartość elementu",
        "4_1_2_element": "element",
        "4_1_2_msg_pattern": '{{msgNodeType}} nie posiada prawidłowej nazwy (np. atrybutu) dla "accessibility API". Dopuszczalne opcje to: {{builtAttrs}}',
        "4_1_2_msg_pattern2": '{{msgNodeType}} nie ma wartości, która mogłaby być dostępna dla "accessibility API".',
        "4_1_2_msg_add_one": "Add one by adding content to the element.",
        "4_1_2_msg_pattern3": "This {{msgNodeType}} does not have an initially selected option. Depending on your HTML version, the value exposed to an accessibility API may be undefined.",
        "4_1_2_value_exposed_using_attribute": "A value is exposed using the {{requiredValue}} attribute.",
        "4_1_2_value_exposed_using_element": "A value is exposed using the {{requiredValue}} element."
    }, _global.translation.es = {
        auditor_name: "HTML_CodeSniffer by Squiz",
        auditor_using_standard: "Utilizando estándar",
        auditor_standards: "Standards",
        auditor_code_snippet: "Code Snippet",
        auditor_close: "Cerrar",
        auditor_select_types: "Select the types of issues to include in the report",
        auditor_home: "Home",
        auditor_view_report: "View Report",
        auditor_report: "Report",
        auditor_back_to_report: "Back to Report",
        auditor_previous_issue: "Previous Issue",
        auditor_next_issue: "Next Issue",
        auditor_issue: "Issue",
        auditor_of: "of",
        auditor_errors: "Errores",
        auditor_error: "Error",
        auditor_warnings: "Warnings",
        auditor_warning: "Warning",
        auditor_notices: "Notices",
        auditor_notice: "Notice",
        auditor_toggle_display_of: "Toggle display of",
        auditor_messages: "messages",
        auditor_unable_to_point: "Unable to point to the element associated with this issue.",
        auditor_unable_to_point_entire: "Unable to point to this issue, as it relates to the entire document.",
        auditor_unable_to_point_removed: "Unable to point to this element as it has been removed from the document since the report was generated.",
        auditor_unable_to_point_outside: "Unable to point to this element because it is located outside the document's body element.",
        auditor_unable_to_point_hidden: "Unable to point to this element because it is hidden from view, or does not have a visual representation.",
        auditor_point_to_element: "Point to Element",
        auditor_unsupported_browser: "La funcionalidad de fragmento de código no es compatible con este navegador.",
        auditor_page: "Page",
        auditor_updated_to: "HTML_CodeSniffer has been updated to version",
        auditor_view_the_changelog: "View the changelog",
        auditor_success_criterion: "Success Criterion",
        auditor_suggested_techniques: "Suggested Techniques",
        auditor_applies_entire_document: "Esto aplica a todo el documento",
        "1_1_1_H30.2": "El elemento Img es el único contenido del enlace, pero le falta texto alternativo. El texto alternativo debe describir el propósito del enlace.",
        "1_1_1_H67.1": "El elemento Img con texto alternativo vacío debe tener un atributo de título ausente o vacío.",
        "1_1_1_H67.2": "El elemento Img está marcado para que la tecnología de asistencia lo ignore.",
        "1_1_1_H37": "Al elemento Img le falta un atributo alt. Use el atributo alt para especificar una alternativa de texto breve.",
        "1_1_1_G94.Image": "Asegúrese de que el texto alternativo del elemento img tenga el mismo propósito y presente la misma información que la imagen.",
        "1_1_1_H36": "Al botón de envío de imagen le falta un atributo alt. Especifique una alternativa de texto que describa la función del botón, utilizando el atributo alt.",
        "1_1_1_G94.Button": "Asegúrese de que el texto alternativo del botón de envío de imagen identifica el propósito del botón.",
        "1_1_1_H24": "Al elemento de área en un mapa de imagen le falta un atributo alt. Cada elemento de área debe tener una alternativa de texto que describa la función del área del mapa de imagen.",
        "1_1_1_H24.2": "Asegúrese de que la alternativa de texto del elemento de área tenga el mismo propósito que la parte de la imagen del mapa de imagen a la que hace referencia.",
        "1_1_1_G73,G74": "Si esta imagen no se puede describir completamente en una alternativa de texto corto, asegúrese de que también esté disponible una alternativa de texto largo, como en el texto del cuerpo o mediante un enlace.",
        "1_1_1_H2.EG5": "El elemento Img dentro de un enlace no debe usar texto alternativo que duplique el contenido de texto del enlace.",
        "1_1_1_H2.EG4": "El elemento Img dentro de un enlace tiene texto alternativo vacío o faltante cuando un enlace al lado contiene texto de enlace. Considere combinar los enlaces.",
        "1_1_1_H2.EG3": "El elemento Img dentro de un enlace no debe usar texto alternativo que duplique el contenido de un enlace de texto al lado.",
        "1_1_1_H53,ARIA6": "Los elementos de objeto deben contener una alternativa de texto después de que se hayan agotado todas las demás alternativas.",
        "1_1_1_G94,G92.Object,ARIA6": "Verifique que las alternativas de texto corto (y si corresponde, largo) estén disponibles para contenido que no sea de texto y que tengan el mismo propósito y presenten la misma información.",
        "1_1_1_H35.3": "Los elementos de applet deben contener una alternativa de texto en el cuerpo del elemento, para navegadores sin soporte para el elemento applet.",
        "1_1_1_H35.2": "Los elementos del applet deben contener un atributo alt, para proporcionar una alternativa de texto a los navegadores que admiten el elemento pero no pueden cargar el applet.",
        "1_1_1_G94,G92.Applet": "Verifique que las alternativas de texto corto (y si corresponde, largo) estén disponibles para contenido que no sea de texto y que tengan el mismo propósito y presenten la misma información.",
        "1_2_1_G158": "Si este objeto incrustado contiene audio pregrabado únicamente y no se proporciona como una alternativa para el contenido de texto, verifique que haya disponible una versión alternativa de texto.",
        "1_2_1_G159,G166": "Si este objeto incrustado contiene solo video pregrabado y no se proporciona como una alternativa para el contenido de texto, verifique que haya disponible una versión alternativa de texto o que se proporcione una pista de audio que presente información equivalente.",
        "1_2_2_G87,G93": "Si este objeto incrustado contiene medios sincronizados pregrabados y no se proporciona como una alternativa para el contenido de texto, verifique que se proporcionen subtítulos para el contenido de audio.",
        "1_2_3_G69,G78,G173,G8": "Si este objeto incrustado contiene medios sincronizados pregrabados y no se proporciona como una alternativa para el contenido de texto, verifique que se proporcione una descripción de audio de su video y / o una versión alternativa de texto del contenido.",
        "1_2_4_G9,G87,G93": "Si este objeto incrustado contiene medios sincronizados, verifique que se proporcionen subtítulos para el contenido de audio en vivo.",
        "1_2_5_G78,G173,G8": "Si este objeto incrustado contiene medios sincronizados pregrabados, verifique que se proporcione una descripción de audio para su contenido de video.",
        "1_2_6_G54,G81": "Si este objeto incrustado contiene medios sincronizados pregrabados, verifique que se proporcione una interpretación del lenguaje de señas para su audio.",
        "1_2_7_G8": "Si este objeto incrustado contiene medios sincronizados, y cuando las pausas en el audio en primer plano no son suficientes para permitir que las descripciones de audio transmitan la sensación de video pregrabado, verifique que se proporcione una descripción de audio extendida, ya sea a través de secuencias de comandos o una versión alternativa.",
        "1_2_8_G69,G159": "Si este objeto incrustado contiene medios sincronizados pregrabados o contenido solo de video, verifique que se proporcione una versión alternativa de texto del contenido.",
        "1_2_9_G150,G151,G157": "Si este objeto incrustado contiene contenido de solo audio en vivo, verifique que se proporcione una versión alternativa de texto del contenido.",
        "1_3_1_F92,ARIA4": 'El rol de este elemento es "presentación" pero contiene elementos secundarios con significado semántico.',
        "1_3_1_H44.NonExistent": 'El atributo "for" de esta etiqueta contiene una ID que no existe en el documento.',
        "1_3_1_H44.NonExistentFragment": 'El atributo "for" de esta etiqueta contiene una ID que no existe en el fragmento de documento.',
        "1_3_1_H44.NotFormControl": 'El atributo "for" de esta etiqueta contiene una ID para un elemento que no es un control de formulario. Asegúrese de haber ingresado la ID correcta para el elemento deseado.',
        "1_3_1_H65": 'Este control de formulario tiene un atributo "título" que está vacío o contiene solo espacios. Será ignorado para propósitos de prueba de etiquetado.',
        "1_3_1_ARIA6": 'Este control de formulario tiene un atributo "aria-label" que está vacío o contiene solo espacios. Será ignorado para propósitos de prueba de etiquetado.',
        "1_3_1_ARIA16,ARIA9": 'Este control de formulario contiene un atributo aria-labelledby, sin embargo, incluye un ID "{{id}}" que no existe en un elemento. El atributo aria-labelledby se ignorará para fines de prueba de etiquetado.',
        "1_3_1_F68.Hidden": "Este campo de formulario oculto está etiquetado de alguna manera. No debería haber necesidad de etiquetar un campo de formulario oculto.",
        "1_3_1_F68.HiddenAttr": 'Este campo de formulario está destinado a estar oculto (utilizando el atributo "oculto"), pero también está etiquetado de alguna manera. No debería haber necesidad de etiquetar un campo de formulario oculto.',
        "1_3_1_F68": 'Este campo de formulario debe estar etiquetado de alguna manera. Use el elemento de etiqueta (ya sea con un atributo "for" o envuelto alrededor del campo de formulario) o los atributos "title", "aria-label" o "aria-labelledby" según corresponda.',
        "1_3_1_H49.": "Marcado de presentación utilizado que se ha vuelto obsoleto en HTML5.",
        "1_3_1_H49.AlignAttr": "Alinear atributos.",
        "1_3_1_H49.Semantic": "El marcado semántico se debe usar para marcar texto resaltado o especial para que pueda determinarse mediante programación.",
        "1_3_1_H49.AlignAttr.Semantic": "El marcado semántico se debe usar para marcar texto resaltado o especial para que pueda determinarse mediante programación.",
        "1_3_1_H42": "Se debe usar el marcado de encabezado si este contenido está pensado como un encabezado.",
        "1_3_1_H63.3": "La celda de la tabla tiene un atributo de alcance no válido. Los valores válidos son row, col, rowgroup o colgroup.",
        "1_3_1_H63.2": "Los atributos de alcance en elementos td que actúan como encabezados para otros elementos son obsoletos en HTML5. Use un elemento th en su lugar.",
        "1_3_1_H43.ScopeAmbiguous": "Los atributos de alcance en los elementos son ambiguos en una tabla con múltiples niveles de encabezados. Utilice el atributo de encabezado en elementos td en su lugar.",
        "1_3_1_H43.IncorrectAttr": 'Atributos de encabezados incorrectos en este elemento td. Se esperaba "{{expected}}" pero se encontró "{{actual}}"',
        "1_3_1_H43.HeadersRequired": "La relación entre los elementos td y sus elementos th asociados no está definida. Como esta tabla tiene varios niveles de elementos th, debe usar el atributo de encabezado en los elementos td.",
        "1_3_1_H43.MissingHeaderIds": "No todos los elementos de esta tabla contienen un atributo id. Estas celdas deben contener identificadores para que puedan ser referenciados por los atributos de encabezados de los elementos td.",
        "1_3_1_H43.MissingHeadersAttrs": "No todos los elementos td en esta tabla contienen un atributo de encabezado. Cada atributo de encabezado debe enumerar los identificadores de todos los elementos asociados con esa celda.",
        "1_3_1_H43,H63": "La relación entre los elementos td y sus elementos th asociados no está definida. Utilice el atributo de alcance en los elementos th o el atributo de encabezado en los elementos td.",
        "1_3_1_H63.1": "No todos los elementos de esta tabla tienen un atributo de alcance. Estas celdas deben contener un atributo de alcance para identificar su asociación con elementos td.",
        "1_3_1_H73.3.LayoutTable": "Parece que esta tabla se usa para el diseño, pero contiene un atributo de resumen. Las tablas de diseño no deben contener atributos de resumen o, si se proporcionan, deben estar vacías.",
        "1_3_1_H39,H73.4": "Si esta tabla es una tabla de datos, y tanto un atributo de resumen como un elemento de título están presentes, el resumen no debe duplicar el título.",
        "1_3_1_H73.3.Check": "Si esta tabla es una tabla de datos, verifique que el atributo de resumen describa la organización de la tabla o explique cómo usarla..",
        "1_3_1_H73.3.NoSummary": "Si esta tabla es una tabla de datos, considere usar el atributo de resumen del elemento de la tabla para dar una visión general de esta tabla.",
        "1_3_1_H39.3.LayoutTable": "Parece que esta tabla se usa para el diseño, pero contiene un elemento de título. Las tablas de diseño no deben contener subtítulos.",
        "1_3_1_H39.3.Check": "Si esta tabla es una tabla de datos, verifique que el elemento de título describa con precisión esta tabla.",
        "1_3_1_H39.3.NoCaption": "Si esta tabla es una tabla de datos, considere usar un elemento de título para el elemento de tabla para identificar esta tabla.",
        "1_3_1_H71.NoLegend": "El conjunto de campos no contiene un elemento de leyenda. Todos los conjuntos de campos deben contener un elemento de leyenda que describa una descripción del grupo de campos.",
        "1_3_1_H85.2": "Si esta lista de selección contiene grupos de opciones relacionadas, deben agruparse con optgroup.",
        "1_3_1_H71.SameName": "Si estos botones de radio o casillas de verificación requieren una descripción adicional a nivel de grupo, deben estar contenidos dentro de un elemento de conjunto de campos.",
        "1_3_1_H48.1": "Parece que este contenido simula una lista desordenada utilizando texto sin formato. Si es así, marcar este contenido con un elemento ul agregaría información de estructura adecuada al documento.",
        "1_3_1_H48.2": "Parece que este contenido está simulando una lista ordenada usando texto sin formato. Si es así, marcar este contenido con un elemento ol agregaría información de estructura adecuada al documento.",
        "1_3_1_G141_a": "La estructura del encabezado no está lógicamente anidada. Este elemento h{{headingNum}} parece ser el encabezado del documento principal, por lo que debería ser un elemento h1.",
        "1_3_1_G141_b": "La estructura del encabezado no está lógicamente anidada. Este elemento h{{headingNum}} debe ser un h{{properHeadingNum}} para estar correctamente anidado.",
        "1_3_1_H42.2": "Etiqueta de encabezado encontrada sin contenido. El texto que no pretende ser un encabezado no debe estar marcado con etiquetas de encabezado.",
        "1_3_1_H48": "Si este elemento contiene una sección de navegación, se recomienda que se marque como una lista.",
        "1_3_1_LayoutTable": "Esta tabla parece ser una tabla de diseño. Si está destinado a ser una tabla de datos, asegúrese de que las celdas del encabezado se identifiquen mediante elementos th.",
        "1_3_1_DataTable": "Esta tabla parece ser una tabla de datos. Si está destinado a ser una tabla de diseño, asegúrese de que no hay elementos th, y no hay resumen o título.",
        "1_3_2_G57": "Compruebe que el contenido se ordena en una secuencia significativa cuando se linealiza, como cuando las hojas de estilo están desactivadas.",
        "1_3_3_G96": "Cuando se proporcionan instrucciones para comprender el contenido, no confíe solo en las características sensoriales (como la forma, el tamaño o la ubicación) para describir los objetos..",
        "1_4_1_G14,G18": "Verifique que cualquier información transmitida usando solo color también esté disponible en texto o mediante otras señales visuales.",
        "1_4_2_F23": "Si este elemento contiene audio que se reproduce automáticamente durante más de 3 segundos, compruebe que existe la posibilidad de pausar, detener o silenciar el audio.",
        "1_4_3_F24.BGColour": "Compruebe que este elemento tiene un color de primer plano heredado para complementar el color de fondo o la imagen en línea correspondiente.",
        "1_4_3_F24.FGColour": "Compruebe que este elemento tiene un color de fondo o imagen heredados para complementar el color de primer plano en línea correspondiente.",
        "1_4_3_G18_or_G145.Abs": "Este elemento está en una posición absoluta y no se puede determinar el color de fondo. Asegúrese de que la relación de contraste entre el texto y todas las partes cubiertas del fondo sea al menos {{required}}: 1.",
        "1_4_3_G18_or_G145.BgImage": "El texto de este elemento se coloca en una imagen de fondo. Asegúrese de que la relación de contraste entre el texto y todas las partes cubiertas de la imagen sea al menos {{required}}: 1.",
        "1_4_3_G18_or_G145.Alpha": "El texto o el fondo de este elemento contiene transparencia. Asegúrese de que la relación de contraste entre el texto y el fondo sea al menos {{required}}: 1.",
        "1_4_3_G18_or_G145.Fail": "Este elemento tiene un contraste insuficiente en este nivel de conformidad. Se esperaba una relación de contraste de al menos {{required}}: 1, pero el texto en este elemento tiene una relación de contraste de {{value}}: 1.",
        "1_4_3_G18_or_G145.Fail.Recomendation": "Recommendation: change",
        "1_4_3_G18_or_G145.Fail.Recomendation.Text": "color de texto a",
        "1_4_3_G18_or_G145.Fail.Recomendation.Background": "antecedentes para",
        "1_4_4_G142": "Compruebe que el texto se puede cambiar de tamaño sin tecnología de asistencia hasta un 200 por ciento sin pérdida de contenido o funcionalidad.",
        "1_4_5_G140,C22,C30.AALevel": "Si las tecnologías que se usan pueden lograr la presentación visual, verifique que el texto se use para transmitir información en lugar de imágenes de texto, excepto cuando la imagen de texto es esencial para la información que se transmite o puede personalizarse visualmente según los requisitos del usuario.",
        "1_4_6_G18_or_G17.Abs": "Este elemento está en una posición absoluta y no se puede determinar el color de fondo. Asegúrese de que la relación de contraste entre el texto y todas las partes cubiertas del fondo sea al menos {{required}}: 1.",
        "1_4_6_G18_or_G17.BgImage": "El texto de este elemento se coloca en una imagen de fondo. Asegúrese de que la relación de contraste entre el texto y todas las partes cubiertas de la imagen sea al menos {{required}}: 1.",
        "1_4_6_G18_or_G17.Fail": "Este elemento tiene un contraste insuficiente en este nivel de conformidad. Se esperaba una relación de contraste de al menos {{required}}: 1, pero el texto en este elemento tiene una relación de contraste de {{value}}: 1.",
        "1_4_6_G18_or_G17.Fail.Recomendation": "Recommendation: change",
        "1_4_6_G18_or_G17.Fail.Recomendation.Text": "color de texto a",
        "1_4_6_G18_or_G17.Fail.Recomendation.Background": "antecedentes para",
        "1_4_7_G56": "Para el contenido de solo audio pregrabado en este elemento que es principalmente el habla (como la narración), cualquier sonido de fondo debe ser silenciable o al menos 20 dB (o aproximadamente 4 veces) más silencioso que el discurso.",
        "1_4_8_G148,G156,G175": "Compruebe que haya un mecanismo disponible para que el usuario seleccione los colores de primer plano y de fondo para los bloques de texto, ya sea a través de la página web o del navegador.",
        "1_4_8_H87,C20": "Verifique que exista un mecanismo para reducir el ancho de un bloque de texto a no más de 80 caracteres (o 40 en escritura china, japonesa o coreana).",
        "1_4_8_C19,G172,G169": "Verifique que los bloques de texto no estén completamente justificados, es decir, en los bordes izquierdo y derecho, o que exista un mecanismo para eliminar la justificación completa.",
        "1_4_8_G188,C21": "Verifique que el espaciado entre líneas en bloques de texto sea al menos 150% en los párrafos, y que el espaciado entre párrafos sea al menos 1.5 veces el espaciado entre líneas, o que haya un mecanismo disponible para lograr esto.",
        "1_4_8_H87,G146,C26": "Compruebe que el texto se puede cambiar de tamaño sin tecnología de asistencia hasta un 200 por ciento sin requerir que el usuario se desplace horizontalmente en una ventana de pantalla completa.",
        "1_4_9_G140,C22,C30.NoException": "Compruebe que las imágenes de texto solo se usan para decoración pura o donde una presentación particular de texto es esencial para la información que se transmite.",
        "2_1_1_G90": "Asegúrese de que la funcionalidad proporcionada por un controlador de eventos para este elemento esté disponible a través del teclado",
        "2_1_1_SCR20.DblClick": "Asegúrese de que la funcionalidad proporcionada al hacer doble clic en este elemento esté disponible a través del teclado.",
        "2_1_1_SCR20.MouseOver": "Asegúrese de que la funcionalidad proporcionada al pasar el mouse sobre este elemento esté disponible a través del teclado; por ejemplo, usando el evento de enfoque.",
        "2_1_1_SCR20.MouseOut": "Asegúrese de que la funcionalidad proporcionada al pasar el mouse por este elemento esté disponible a través del teclado; por ejemplo, usando el evento de desenfoque.",
        "2_1_1_SCR20.MouseMove": "Asegúrese de que la funcionalidad proporcionada al mover el mouse sobre este elemento esté disponible a través del teclado.",
        "2_1_1_SCR20.MouseDown": "Asegúrese de que la funcionalidad proporcionada al pasar el mouse sobre este elemento esté disponible a través del teclado; por ejemplo, usando el evento keydown.",
        "2_1_1_SCR20.MouseUp": "Asegúrese de que la funcionalidad proporcionada al pasar el mouse sobre este elemento esté disponible a través del teclado; por ejemplo, usando el evento keyup.",
        "2_1_2_F10": "Compruebe que este applet o complemento proporciona la capacidad de alejar el foco de sí mismo cuando usa el teclado.",
        "2_2_1_F40.2": "Metaetiqueta de actualización utilizada para redirigir a otra página, con un límite de tiempo que no es cero. Los usuarios no pueden controlar este límite de tiempo.",
        "2_2_1_F41.2": "Metaetiqueta de actualización utilizada para actualizar la página actual. Los usuarios no pueden controlar el límite de tiempo para esta actualización.",
        "2_2_2_SCR33,SCR22,G187,G152,G186,G191": "Si alguna parte del contenido se mueve, se desplaza o parpadea durante más de 5 segundos, o se actualiza automáticamente, verifique que haya un mecanismo disponible para pausar, detener u ocultar el contenido.",
        "2_2_2_F4": "Asegúrese de que haya un mecanismo disponible para detener este elemento parpadeante en menos de cinco segundos.",
        "2_2_2_F47": "Los elementos de parpadeo no pueden satisfacer el requisito de que la información de parpadeo se pueda detener en cinco segundos.",
        "2_2_3_G5": "Verifique que el tiempo no sea una parte esencial del evento o actividad presentada por el contenido, excepto para medios sincronizados no interactivos y eventos en tiempo real.",
        "2_2_4_SCR14": "Compruebe que el usuario puede posponer o suprimir todas las interrupciones (incluidas las actualizaciones de contenido), excepto las interrupciones que involucren una emergencia.",
        "2_2_5_G105,G181": "Si esta página web forma parte de un conjunto de páginas web con un límite de tiempo de inactividad, verifique que un usuario autenticado pueda continuar la actividad sin pérdida de datos después de volver a autenticarse.",
        "2_3_1_G19,G176": "Verifique que ningún componente del contenido parpadee más de tres veces en un período de 1 segundo, o que el tamaño de cualquier área de parpadeo sea lo suficientemente pequeño.",
        "2_3_2_G19": "Compruebe que ningún componente del contenido parpadee más de tres veces en un período de 1 segundo.",
        "2_4_1_H64.1": "El elemento iframe requiere un atributo de título no vacío que identifica el marco.",
        "2_4_1_H64.2": "Verifique que el atributo de título de este elemento contenga texto que identifique el marco.",
        "2_4_1_G1,G123,G124,H69": "Asegúrese de que cualquier elemento de navegación común se pueda omitir; por ejemplo, mediante el uso de enlaces de omisión, elementos de encabezado o roles de referencia de ARIA.",
        "2_4_1_G1,G123,G124.NoSuchID": 'Este enlace apunta a un ancla con nombre "{{id}}" dentro del documento, pero no existe un ancla con ese nombre.',
        "2_4_1_G1,G123,G124.NoSuchIDFragment": 'Este enlace apunta a un ancla con nombre "{{id}}" dentro del documento, pero no existe un ancla con ese nombre en el fragmento probado.',
        "2_4_2_H25.1.NoHeadEl": "No hay una sección de encabezado en la que colocar un elemento de título descriptivo.",
        "2_4_2_H25.1.NoTitleEl": "Se debe proporcionar un título para el documento, utilizando un elemento de título no vacío en la sección de encabezado.",
        "2_4_2_H25.1.EmptyTitle": "El elemento del título en la sección de encabezado no debe estar vacío.",
        "2_4_2_H25.2": "Compruebe que el elemento del título describe el documento.",
        "2_4_3_H4.2": "Si se usa tabindex, verifique que el orden de tabulación especificado por los atributos de tabindex siga las relaciones en el contenido.",
        "2_4_4_H77,H78,H79,H80,H81,H33": "Compruebe que el texto del enlace combinado con el contexto del enlace determinado mediante programación, o su atributo de título, identifique el propósito del enlace.",
        "2_4_4_H77,H78,H79,H80,H81": "Compruebe que el texto del enlace combinado con el contexto del enlace determinado mediante programación identifica el propósito del enlace.",
        "2_4_5_G125,G64,G63,G161,G126,G185": "Si esta página web no es parte de un proceso lineal, verifique que haya más de una forma de ubicar esta página web dentro de un conjunto de páginas web.",
        "2_4_6_G130,G131": "Verifique que los títulos y las etiquetas describan el tema o el propósito.",
        "2_4_7_G149,G165,G195,C15,SCR31": "Verifique que haya al menos un modo de operación donde el indicador de enfoque del teclado pueda ubicarse visualmente en los controles de la interfaz de usuario.",
        "2_4_8_H59.1": "Los elementos de enlace solo pueden ubicarse en la sección del encabezado del documento.",
        "2_4_8_H59.2a": "Al elemento de enlace le falta un atributo rel no vacío que identifica el tipo de enlace.",
        "2_4_8_H59.2b": "Al elemento de enlace le falta un atributo href no vacío que apunta al recurso que se está vinculando.",
        "2_4_9_H30": "Compruebe que el texto del enlace describe el propósito del enlace.",
        "3_1_1_H57.2": "El elemento html debe tener un atributo lang o xml: lang que describe el idioma del documento.",
        "3_1_1_H57.3.Lang": "El idioma especificado en el atributo lang del elemento del documento no parece estar bien formado.",
        "3_1_1_H57.3.XmlLang": "El lenguaje especificado en el atributo xml: lang del elemento del documento no parece estar bien formado.",
        "3_1_2_H58": "Asegúrese de que cualquier cambio en el idioma esté marcado con el atributo lang y / o xml: lang en un elemento, según corresponda.",
        "3_1_2_H58.1.Lang": "El idioma especificado en el atributo lang de este elemento no parece estar bien formado.",
        "3_1_2_H58.1.XmlLang": "El lenguaje especificado en el atributo xml: lang de este elemento no parece estar bien formado.",
        "3_1_3_H40,H54,H60,G62,G70": "Verifique que haya un mecanismo disponible para identificar definiciones específicas de palabras o frases usadas de manera inusual o restringida, incluyendo modismos y jerga.",
        "3_1_4_G102,G55,G62,H28,G97": "Verifique que esté disponible un mecanismo para identificar la forma expandida o el significado de las abreviaturas.",
        "3_1_5_G86,G103,G79,G153,G160": "Cuando el contenido requiere capacidad de lectura más avanzada que el nivel de educación secundaria inferior, se debe proporcionar contenido complementario o una versión alternativa.",
        "3_1_6_H62.1.HTML5": "El elemento ruby no contiene un elemento rt que contiene información de pronunciación para su texto corporal.",
        "3_1_6_H62.1.XHTML11": "El elemento ruby no contiene un elemento rt que contenga información de pronunciación para el texto dentro del elemento rb.",
        "3_1_6_H62.2": "El elemento ruby no contiene elementos rp, que proporcionan puntuación adicional a los navegadores que no admiten texto ruby.",
        "3_2_1_G107": "Compruebe que no se produce un cambio de contexto cuando este campo de entrada recibe el foco.",
        "3_2_2_H32.2": 'Este formulario no contiene un botón de envío, lo que crea problemas para aquellos que no pueden enviar el formulario usando el teclado. Los botones de envío son elementos de ENTRADA con el atributo de tipo "enviar" o "imagen", o elementos de BOTÓN con el tipo de "enviar" u omitidos / inválidos.',
        "3_2_3_G61": "Compruebe que los mecanismos de navegación que se repiten en varias páginas web se producen en el mismo orden relativo cada vez que se repiten, a menos que el usuario inicie un cambio.",
        "3_2_4_G197": "Verifique que los componentes que tienen la misma funcionalidad dentro de esta página web se identifiquen consistentemente en el conjunto de páginas web a las que pertenece.",
        "3_2_5_H83.3": "Compruebe que el texto del enlace de este enlace contiene información que indica que el enlace se abrirá en una nueva ventana.",
        "3_3_1_G83,G84,G85": "Si se detecta automáticamente un error de entrada en este formulario, verifique que los elementos con error estén identificados y que los errores se describan al usuario en un texto.",
        "3_3_2_G131,G89,G184,H90": "Compruebe que se proporcionan etiquetas o instrucciones descriptivas (incluidos los campos obligatorios) para la entrada del usuario en este formulario.",
        "3_3_3_G177": "Compruebe que este formulario proporciona correcciones sugeridas a los errores en la entrada del usuario, a menos que ponga en peligro la seguridad o el propósito del contenido.",
        "3_3_4_G98,G99,G155,G164,G168.LegalForms": "Si este formulario obligaría a un usuario a un compromiso financiero o legal, modificaría / eliminaría datos controlables por el usuario o enviaría respuestas de prueba, asegúrese de que los envíos sean reversibles, verificados por errores de entrada y / o confirmados por el usuario.",
        "3_3_5_G71,G184,G193": "Verifique que la ayuda contextual esté disponible para este formulario, a nivel de página web y / o control.",
        "3_3_6_G98,G99,G155,G164,G168.AllForms": "Verifique que los envíos a este formulario sean reversibles, verificados por errores de entrada y / o confirmados por el usuario.",
        "4_1_1_F77": 'Se ha encontrado el valor de atributo de identificación duplicado "{{id}}" en la página web.',
        "4_1_2_H91.A.Empty": "Elemento de anclaje encontrado con un ID pero sin un href o texto de enlace. Considere mover su ID a un elemento primario o cercano.",
        "4_1_2_H91.A.EmptyWithName": "Elemento de anclaje encontrado con un atributo de nombre pero sin href o texto de enlace. Considere mover el atributo de nombre para convertirse en una ID de un elemento primario o cercano.",
        "4_1_2_H91.A.EmptyNoId": "Elemento de anclaje encontrado sin contenido de enlace y sin nombre y / o atributo ID.",
        "4_1_2_H91.A.NoHref": "Los elementos de anclaje no deben usarse para definir objetivos de enlace en la página. Si no usa la ID para otros fines (como CSS o secuencias de comandos), considere moverla a un elemento principal.",
        "4_1_2_H91.A.Placeholder": "Elemento de anclaje encontrado con contenido de enlace, pero no se ha proporcionado ningún atributo href, ID o nombre.",
        "4_1_2_H91.A.NoContent": "Elemento de anclaje encontrado con un atributo href válido, pero no se ha proporcionado contenido de enlace.",
        "4_1_2_input_element": "elemento de entrada",
        "4_1_2_role_of_button": 'el elemento tiene el rol de "botón" pero',
        "4_1_2_element_content": "contenido del elemento",
        "4_1_2_element": "elemento",
        "4_1_2_msg_pattern": "Este {{msgNodeType}} no tiene un nombre disponible para una API de accesibilidad. Los nombres válidos son: {{builtAttrs}}.",
        "4_1_2_msg_pattern2": "Este {{msgNodeType}} no tiene un valor disponible para una API de accesibilidad.",
        "4_1_2_msg_add_one": "Agregue uno agregando contenido al elemento.",
        "4_1_2_msg_pattern3": "Este {{msgNodeType}} no tiene una opción seleccionada inicialmente. Dependiendo de su versión HTML, el valor expuesto a una API de accesibilidad puede ser indefinido.",
        "4_1_2_value_exposed_using_attribute": "Un valor se expone utilizando el atributo {{requiredValue}}.",
        "4_1_2_value_exposed_using_element": "Un valor se expone utilizando el elemento {{requiredValue}}."
    }, _global.HTMLCS_Section508 = {
        name: "Section508",
        description: "U.S. Section 508 Standard",
        sniffs: ["A", "B", "C", "D", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"],
        getMsgInfo: function(a) {
            return [
                ["Section", "1194.22 (" + a.split(".", 3)[1].toLowerCase() + ")"]
            ]
        }
    }, _global.HTMLCS_Section508_Sniffs_A = {
        register: function() {
            return ["_top", "img", "object", "bgsound", "audio"]
        },
        process: function(a, b) {
            if (a === b) this.addNullAltTextResults(b), this.addMediaAlternativesResults(b);
            else {
                var c = a.nodeName.toLowerCase();
                "object" !== c && "bgsound" !== c && "audio" !== c || HTMLCS.addMessage(HTMLCS.NOTICE, a, "For multimedia containing audio only, ensure an alternative is available, such as a full text transcript.", "Audio")
            }
        },
        testNullAltText: function(a) {
            var b = {
                img: {
                    generalAlt: [],
                    missingAlt: [],
                    ignored: [],
                    nullAltWithTitle: [],
                    emptyAltInLink: []
                },
                inputImage: {
                    generalAlt: [],
                    missingAlt: []
                },
                area: {
                    generalAlt: [],
                    missingAlt: []
                }
            };
            elements = HTMLCS.util.getAllElements(a, 'img, area, input[type="image"]');
            for (var c = 0; c < elements.length; c++) {
                var d = elements[c],
                    e = d.nodeName.toLowerCase(),
                    f = !1,
                    g = !1,
                    h = !1;
                if ("a" === d.parentNode.nodeName.toLowerCase()) {
                    var i = HTMLCS.util.getPreviousSiblingElement(d, null),
                        j = HTMLCS.util.getNextSiblingElement(d, null);
                    if (null === i && null === j) {
                        var k = d.parentNode.textContent;
                        if (void 0 !== d.parentNode.textContent) var k = d.parentNode.textContent;
                        else var k = d.parentNode.innerText;
                        !0 === HTMLCS.util.isStringEmpty(k) && (f = !0)
                    }
                }
                switch (!1 === d.hasAttribute("alt") ? g = !0 : d.getAttribute("alt") && !0 !== HTMLCS.util.isStringEmpty(d.getAttribute("alt")) || (h = !0), e) {
                    case "img":
                        !0 !== f || !0 !== g && !0 !== h ? !0 === g ? b.img.missingAlt.push(d) : !0 === h ? !0 === d.hasAttribute("title") && !1 === HTMLCS.util.isStringEmpty(d.getAttribute("title")) ? b.img.nullAltWithTitle.push(d) : b.img.ignored.push(d) : b.img.generalAlt.push(d) : b.img.emptyAltInLink.push(d.parentNode);
                        break;
                    case "input":
                        !0 === g || !0 === h ? b.inputImage.missingAlt.push(d) : b.inputImage.generalAlt.push(d);
                        break;
                    case "area":
                        !0 === g || !0 === h ? b.area.missingAlt.push(d) : b.inputImage.generalAlt.push(d)
                }
            }
            return b
        },
        addNullAltTextResults: function(a) {
            for (var b = this.testNullAltText(a), c = 0; c < b.img.emptyAltInLink.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.img.emptyAltInLink[c], "Img element is the only content of the link, but is missing alt text. The alt text should describe the purpose of the link.", "Img.EmptyAltInLink");
            for (var c = 0; c < b.img.nullAltWithTitle.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.img.nullAltWithTitle[c], "Img element with empty alt text must have absent or empty title attribute.", "Img.NullAltWithTitle");
            for (var c = 0; c < b.img.ignored.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, b.img.ignored[c], "Img element is marked so that it is ignored by Assistive Technology.", "Img.Ignored");
            for (var c = 0; c < b.img.missingAlt.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.img.missingAlt[c], "Img element missing an alt attribute. Use the alt attribute to specify a short text alternative.", "Img.MissingAlt");
            for (var c = 0; c < b.img.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.img.generalAlt[c], "Ensure that the img element's alt text serves the same purpose and presents the same information as the image.", "Img.GeneralAlt");
            for (var c = 0; c < b.inputImage.missingAlt.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.inputImage.missingAlt[c], "Image submit button missing an alt attribute. Specify a text alternative that describes the button's function, using the alt attribute.", "InputImage.MissingAlt");
            for (var c = 0; c < b.inputImage.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.inputImage.generalAlt[c], "Ensure that the image submit button's alt text identifies the purpose of the button.", "InputImage.GeneralAlt");
            for (var c = 0; c < b.area.missingAlt.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.area.missingAlt[c], "Area element in an image map missing an alt attribute. Each area element must have a text alternative that describes the function of the image map area.", "Area.MissingAlt");
            for (var c = 0; c < b.area.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.area.generalAlt[c], "Ensure that the area element's text alternative serves the same purpose as the part of image map image it references.", "Area.GeneralAlt")
        },
        testMediaTextAlternatives: function(a) {
            for (var b = {
                    object: {
                        missingBody: [],
                        generalAlt: []
                    },
                    applet: {
                        missingBody: [],
                        missingAlt: [],
                        generalAlt: []
                    }
                }, c = HTMLCS.util.getAllElements(a, "object"), d = 0; d < c.length; d++) {
                var e = c[d],
                    f = (e.nodeName.toLowerCase(), e.querySelector("object"));
                if (null === f) {
                    var g = HTMLCS.util.getElementTextContent(e, !0);
                    "" === g ? b.object.missingBody.push(e) : b.object.generalAlt.push(e)
                }
            }
            for (var c = HTMLCS.util.getAllElements(a, "applet"), d = 0; d < c.length; d++) {
                var f = e.querySelector("object"),
                    h = !1;
                if (null === f) {
                    var g = HTMLCS.util.getElementTextContent(e, !0);
                    !0 === HTMLCS.util.isStringEmpty(g) && (b.applet.missingBody.push(e), h = !0)
                }
                var i = e.getAttribute("alt") || "";
                !0 === HTMLCS.util.isStringEmpty(i) && (b.applet.missingAlt.push(e), h = !0), !1 === h && b.applet.generalAlt.push(e)
            }
            return b
        },
        addMediaAlternativesResults: function(a) {
            for (var b = HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_1_1_1_1.testMediaTextAlternatives(a), c = 0; c < b.object.missingBody.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.object.missingBody[c], "Object elements must contain a text alternative after all other alternatives are exhausted.", "Object.MissingBody");
            for (var c = 0; c < b.object.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.object.generalAlt[c], "Check that short (and if appropriate, long) text alternatives are available for non-text content that serve the same purpose and present the same information.", "Object.GeneralAlt");
            for (var c = 0; c < b.applet.missingBody.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.applet.missingBody[c], "Applet elements must contain a text alternative in the element's body, for browsers without support for the applet element.", "Applet.MissingBody");
            for (var c = 0; c < b.applet.missingAlt.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.applet.missingAlt[c], "Applet elements must contain an alt attribute, to provide a text alternative to browsers supporting the element but are unable to load the applet.", "Applet.MissingAlt");
            for (var c = 0; c < b.applet.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.applet.generalAlt[c], "Check that short (and if appropriate, long) text alternatives are available for non-text content that serve the same purpose and present the same information.", "Applet.GeneralAlt")
        }
    }, _global.HTMLCS_Section508_Sniffs_B = {
        register: function() {
            return ["object", "applet", "embed", "video"]
        },
        process: function(a, b) {
            a.nodeName.toLowerCase();
            HTMLCS.addMessage(HTMLCS.NOTICE, a, "For multimedia containing video, ensure a synchronised audio description or text alternative for the video portion is provided.", "Video"), HTMLCS.addMessage(HTMLCS.NOTICE, a, "For multimedia containing synchronised audio and video, ensure synchronised captions are provided for the audio portion.", "Captions")
        }
    }, _global.HTMLCS_Section508_Sniffs_C = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, "Ensure that any information conveyed using colour alone is also available without colour, such as through context or markup.", "Colour")
        }
    }, _global.HTMLCS_Section508_Sniffs_D = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            if (a === b) {
                HTMLCS.addMessage(HTMLCS.NOTICE, b, "Ensure that content is ordered in a meaningful sequence when linearised, such as when style sheets are disabled.", "Linearised"), this.testPresentationMarkup(b), this.testHeadingOrder(b);
                HTMLCS.util.getAllElements(b, 'script, link[rel="stylesheet"]').length > 0 && HTMLCS.addMessage(HTMLCS.NOTICE, b, 'If content is hidden and made visible using scripting (such as "click to expand" sections), ensure this content is readable when scripts and style sheets are disabled.', "HiddenText")
            }
        },
        testPresentationMarkup: function(a) {
            _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_1.testPresentationMarkup(a)
        },
        testHeadingOrder: function(a) {
            for (var b = 0, c = HTMLCS.util.getAllElements(a, "h1, h2, h3, h4, h5, h6"), d = 0; d < c.length; d++) {
                var e = parseInt(c[d].nodeName.substr(1, 1));
                if (e - b > 1) {
                    var f = "should be an h" + (b + 1) + " to be properly nested";
                    0 === b && (f = "appears to be the primary document heading, so should be an h1 element"), HTMLCS.addMessage(HTMLCS.ERROR, c[d], "The heading structure is not logically nested. This h" + e + " element " + f + ".", "HeadingOrder")
                }
                b = e
            }
        }
    }, _global.HTMLCS_Section508_Sniffs_G = {
        register: function() {
            return ["table"]
        },
        process: function(a, b) {
            !0 === HTMLCS.util.isLayoutTable(a) && HTMLCS.addMessage(HTMLCS.NOTICE, a, "This table has no headers. If this is a data table, ensure row and column headers are identified using th elements.", "TableHeaders")
        }
    }, _global.HTMLCS_Section508_Sniffs_H = {
        register: function() {
            return ["table"]
        },
        process: function(a, b) {
            for (var c = HTMLCS.util.testTableHeaders(a), d = 0; d < c.wrongHeaders.length; d++) HTMLCS.addMessage(HTMLCS.ERROR, c.wrongHeaders[d].element, 'Incorrect headers attribute on this td element. Expected "' + c.wrongHeaders[d].expected + '" but found "' + c.wrongHeaders[d].actual + '"', "IncorrectHeadersAttr");
            !0 === c.required && !1 === c.allowScope && (!1 === c.used ? HTMLCS.addMessage(HTMLCS.ERROR, a, "The relationship between td elements and their associated th elements is not defined. As this table has multiple levels of th elements, you must use the headers attribute on td elements.", "MissingHeadersAttrs") : (c.missingThId.length > 0 && HTMLCS.addMessage(HTMLCS.ERROR, a, "Not all th elements in this table contain an id attribute. These cells should contain ids so that they may be referenced by td elements' headers attributes.", "MissingHeaderIds"), c.missingTd.length > 0 && HTMLCS.addMessage(HTMLCS.ERROR, a, "Not all td elements in this table contain a headers attribute. Each headers attribute should list the ids of all th elements associated with that cell.", "IncompleteHeadersAttrs")))
        }
    }, _global.HTMLCS_Section508_Sniffs_I = {
        register: function() {
            return ["frame", "iframe", "object"]
        },
        process: function(a, b) {
            var c = a.nodeName.toLowerCase(),
                d = a.hasAttribute("title"),
                e = !0;
            !0 === d && (e = HTMLCS.util.isStringEmpty(a.getAttribute("title"))), !0 === e && HTMLCS.addMessage(HTMLCS.ERROR, b, "This " + c + " element is missing title text. Frames should be titled with text that facilitates frame identification and navigation.", "Frames")
        }
    }, _global.HTMLCS_Section508_Sniffs_J = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, "Check that no component of the content flickers at a rate of greater than 2 and less than 55 times per second.", "Flicker")
        }
    }, _global.HTMLCS_Section508_Sniffs_K = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, "If this page cannot be made compliant, a text-only page with equivalent information or functionality should be provided. The alternative page needs to be updated in line with this page's content.", "AltVersion")
        }
    }, _global.HTMLCS_Section508_Sniffs_L = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            a === b && (this.addProcessLinksMessages(b), this.testKeyboard(b))
        },
        addProcessLinksMessages: function(a) {
            for (var b = this.processLinks(a), c = 0; c < b.emptyNoId.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.emptyNoId[c], "Anchor element found with no link content and no name and/or ID attribute.", "EmptyAnchorNoId");
            for (var c = 0; c < b.placeholder.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, b.placeholder[c], "Anchor element found with link content, but no href, ID, or name attribute has been supplied.", "PlaceholderAnchor");
            for (var c = 0; c < b.noContent.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.noContent[c], "Anchor element found with a valid href attribute, but no link content has been supplied.", "NoContentAnchor")
        },
        processLinks: function(a) {
            for (var b = {
                    empty: [],
                    emptyWithName: [],
                    emptyNoId: [],
                    noHref: [],
                    placeholder: [],
                    noContent: []
                }, c = HTMLCS.util.getAllElements(a, "a"), d = 0; d < c.length; d++) {
                var e = c[d],
                    f = !1,
                    g = HTMLCS.util.getElementTextContent(e);
                !0 === e.hasAttribute("title") && !1 === /^\s*$/.test(e.getAttribute("title")) ? !0 : !1 === /^\s*$/.test(g) && !0, !0 === e.hasAttribute("href") && !1 === /^\s*$/.test(e.getAttribute("href")) && (f = !0), !1 === f ? !0 === /^\s*$/.test(g) ? !0 === e.hasAttribute("id") ? b.empty.push(e) : !0 === e.hasAttribute("name") ? b.emptyWithName.push(e) : b.emptyNoId.push(e) : !0 === e.hasAttribute("id") || !0 === e.hasAttribute("name") ? b.noHref.push(e) : b.placeholder.push(e) : !0 === /^\s*$/.test(g) && 0 === e.querySelectorAll("img").length && b.noContent.push(e)
            }
            return b
        },
        testKeyboard: function(a) {
            for (var b = HTMLCS.util.getAllElements(a, "*[ondblclick]"), c = 0; c < b.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, b[c], "Ensure the functionality provided by double-clicking on this element is available through the keyboard.", "DblClick");
            for (var d = HTMLCS.util.getAllElements(a, "*[onmouseover]"), c = 0; c < d.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, d[c], "Ensure the functionality provided by mousing over this element is available through the keyboard; for instance, using the focus event.", "MouseOver");
            for (var e = HTMLCS.util.getAllElements(a, "*[onmouseout]"), c = 0; c < e.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, e[c], "Ensure the functionality provided by mousing out of this element is available through the keyboard; for instance, using the blur event.", "MouseOut");
            for (var f = HTMLCS.util.getAllElements(a, "*[onmousemove]"), c = 0; c < f.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, f[c], "Ensure the functionality provided by moving the mouse on this element is available through the keyboard.", "MouseMove");
            for (var g = HTMLCS.util.getAllElements(a, "*[onmousedown]"), c = 0; c < g.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, g[c], "Ensure the functionality provided by mousing down on this element is available through the keyboard; for instance, using the keydown event.", "MouseDown");
            for (var h = HTMLCS.util.getAllElements(a, "*[onmouseup]"), c = 0; c < h.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, h[c], "Ensure the functionality provided by mousing up on this element is available through the keyboard; for instance, using the keyup event.", "MouseUp")
        }
    }, _global.HTMLCS_Section508_Sniffs_M = {
        register: function() {
            return ["object", "applet", "bgsound", "embed", "audio", "video"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, "If external media requires a plugin or application to view, ensure a link is provided to a plugin or application that complies with Section 508 accessibility requirements for applications.", "PluginLink")
        }
    }, _global.HTMLCS_Section508_Sniffs_N = {
        register: function() {
            return ["form"]
        },
        process: function(a, b) {
            "form" === a.nodeName.toLowerCase() && (HTMLCS.addMessage(HTMLCS.NOTICE, a, "If an input error is automatically detected in this form, check that the item(s) in error are identified and the error(s) are described to the user in text.", "Errors"), HTMLCS.addMessage(HTMLCS.NOTICE, a, "Check that descriptive labels or instructions (including for required fields) are provided for user input in this form.", "Labels"), HTMLCS.addMessage(HTMLCS.NOTICE, a, "Ensure that this form can be navigated using the keyboard and other accessibility tools.", "KeyboardNav"))
        }
    }, _global.HTMLCS_Section508_Sniffs_O = {
        register: function() {
            return ["_top", "a", "area"]
        },
        process: function(a, b) {
            if (a === b) HTMLCS.addMessage(HTMLCS.NOTICE, b, "Ensure that any common navigation elements can be bypassed; for instance, by use of skip links, header elements, or ARIA landmark roles.", "SkipLinks");
            else if (!0 === a.hasAttribute("href")) {
                var c = a.getAttribute("href");
                if (c = HTMLCS.util.trim(c), c.length > 1 && "#" === c.charAt(0)) {
                    var d = c.substr(1);
                    try {
                        var e = b;
                        e.ownerDocument && (e = e.ownerDocument);
                        var f = e.getElementById(d);
                        null === f && (f = e.querySelector('a[name="' + d + '"]')), null !== f && !1 !== HTMLCS.util.contains(b, f) || (!0 === HTMLCS.isFullDoc(b) || "body" === b.nodeName.toLowerCase() ? HTMLCS.addMessage(HTMLCS.ERROR, a, 'This link points to a named anchor "' + d + '" within the document, but no anchor exists with that name.', "NoSuchID") : HTMLCS.addMessage(HTMLCS.WARNING, a, 'This link points to a named anchor "' + d + '" within the document, but no anchor exists with that name in the fragment tested.', "NoSuchIDFragment"))
                    } catch (a) {}
                }
            }
        }
    }, _global.HTMLCS_Section508_Sniffs_P = {
        register: function() {
            return ["_top", "meta"]
        },
        process: function(a, b) {
            a === b ? HTMLCS.addMessage(HTMLCS.NOTICE, b, "If a timed response is required on this page, alert the user and provide sufficient time to allow them to indicate that more time is required.", "TimeLimit") : !0 === a.hasAttribute("http-equiv") && "refresh" === String(a.getAttribute("http-equiv")).toLowerCase() && !0 === /^[1-9]\d*/.test(a.getAttribute("content").toLowerCase()) && (!0 === /url=/.test(a.getAttribute("content").toLowerCase()) ? HTMLCS.addMessage(HTMLCS.ERROR, a, "Meta refresh tag used to redirect to another page, with a time limit that is not zero. Users cannot control this time limit.", "MetaRedirect") : HTMLCS.addMessage(HTMLCS.ERROR, a, "Meta refresh tag used to refresh the current page. Users cannot control the time limit for this refresh.", "MetaRefresh"))
        }
    }, _global.HTMLCS_WCAG2A = {
        name: "WCAG2A",
        description: "Web Content Accessibility Guidelines (WCAG) 2.0 A",
        sniffs: [{
            standard: "WCAG2AAA",
            include: ["Principle1.Guideline1_1.1_1_1", "Principle1.Guideline1_2.1_2_1", "Principle1.Guideline1_2.1_2_2", "Principle1.Guideline1_2.1_2_3", "Principle1.Guideline1_3.1_3_1", "Principle1.Guideline1_3.1_3_1_A", "Principle1.Guideline1_3.1_3_2", "Principle1.Guideline1_3.1_3_3", "Principle1.Guideline1_4.1_4_1", "Principle1.Guideline1_4.1_4_2", "Principle2.Guideline2_1.2_1_1", "Principle2.Guideline2_1.2_1_2", "Principle2.Guideline2_2.2_2_1", "Principle2.Guideline2_2.2_2_2", "Principle2.Guideline2_3.2_3_1", "Principle2.Guideline2_4.2_4_1", "Principle2.Guideline2_4.2_4_2", "Principle2.Guideline2_4.2_4_3", "Principle2.Guideline2_4.2_4_4", "Principle3.Guideline3_1.3_1_1", "Principle3.Guideline3_2.3_2_1", "Principle3.Guideline3_2.3_2_2", "Principle3.Guideline3_3.3_3_1", "Principle3.Guideline3_3.3_3_2", "Principle4.Guideline4_1.4_1_1", "Principle4.Guideline4_1.4_1_2"]
        }],
        getMsgInfo: function(a) {
            return HTMLCS_WCAG2AAA.getMsgInfo(a)
        }
    }, _global.HTMLCS_WCAG2AA = {
        name: "WCAG2AA",
        description: "Web Content Accessibility Guidelines (WCAG) 2.0 AA",
        sniffs: [{
            standard: "WCAG2AAA",
            include: ["Principle1.Guideline1_1.1_1_1", "Principle1.Guideline1_2.1_2_1", "Principle1.Guideline1_2.1_2_2", "Principle1.Guideline1_2.1_2_4", "Principle1.Guideline1_2.1_2_5", "Principle1.Guideline1_3.1_3_1", "Principle1.Guideline1_3.1_3_1_A", "Principle1.Guideline1_3.1_3_2", "Principle1.Guideline1_3.1_3_3", "Principle1.Guideline1_4.1_4_1", "Principle1.Guideline1_4.1_4_2", "Principle1.Guideline1_4.1_4_3", "Principle1.Guideline1_4.1_4_3_F24", "Principle1.Guideline1_4.1_4_3_Contrast", "Principle1.Guideline1_4.1_4_4", "Principle1.Guideline1_4.1_4_5", "Principle2.Guideline2_1.2_1_1", "Principle2.Guideline2_1.2_1_2", "Principle2.Guideline2_2.2_2_1", "Principle2.Guideline2_2.2_2_2", "Principle2.Guideline2_3.2_3_1", "Principle2.Guideline2_4.2_4_1", "Principle2.Guideline2_4.2_4_2", "Principle2.Guideline2_4.2_4_3", "Principle2.Guideline2_4.2_4_4", "Principle2.Guideline2_4.2_4_5", "Principle2.Guideline2_4.2_4_6", "Principle2.Guideline2_4.2_4_7", "Principle3.Guideline3_1.3_1_1", "Principle3.Guideline3_1.3_1_2", "Principle3.Guideline3_2.3_2_1", "Principle3.Guideline3_2.3_2_2", "Principle3.Guideline3_2.3_2_3", "Principle3.Guideline3_2.3_2_4", "Principle3.Guideline3_3.3_3_1", "Principle3.Guideline3_3.3_3_2", "Principle3.Guideline3_3.3_3_3", "Principle3.Guideline3_3.3_3_4", "Principle4.Guideline4_1.4_1_1", "Principle4.Guideline4_1.4_1_2"]
        }],
        getMsgInfo: function(a) {
            return HTMLCS_WCAG2AAA.getMsgInfo(a)
        }
    }, _global.HTMLCS_WCAG2AAA = {
        name: "WCAG2AAA",
        description: "Web Content Accessibility Guidelines (WCAG) 2.0 AAA",
        sniffs: ["Principle1.Guideline1_1.1_1_1", "Principle1.Guideline1_2.1_2_1", "Principle1.Guideline1_2.1_2_2", "Principle1.Guideline1_2.1_2_4", "Principle1.Guideline1_2.1_2_5", "Principle1.Guideline1_2.1_2_6", "Principle1.Guideline1_2.1_2_7", "Principle1.Guideline1_2.1_2_8", "Principle1.Guideline1_2.1_2_9", "Principle1.Guideline1_3.1_3_1", "Principle1.Guideline1_3.1_3_1_AAA", "Principle1.Guideline1_3.1_3_2", "Principle1.Guideline1_3.1_3_3", "Principle1.Guideline1_4.1_4_1", "Principle1.Guideline1_4.1_4_2", "Principle1.Guideline1_4.1_4_3_F24", "Principle1.Guideline1_4.1_4_3_Contrast", "Principle1.Guideline1_4.1_4_6", "Principle1.Guideline1_4.1_4_7", "Principle1.Guideline1_4.1_4_8", "Principle1.Guideline1_4.1_4_9", "Principle2.Guideline2_1.2_1_1", "Principle2.Guideline2_1.2_1_2", "Principle2.Guideline2_2.2_2_2", "Principle2.Guideline2_2.2_2_3", "Principle2.Guideline2_2.2_2_4", "Principle2.Guideline2_2.2_2_5", "Principle2.Guideline2_3.2_3_2", "Principle2.Guideline2_4.2_4_1", "Principle2.Guideline2_4.2_4_2", "Principle2.Guideline2_4.2_4_3", "Principle2.Guideline2_4.2_4_5", "Principle2.Guideline2_4.2_4_6", "Principle2.Guideline2_4.2_4_7", "Principle2.Guideline2_4.2_4_8", "Principle2.Guideline2_4.2_4_9", "Principle3.Guideline3_1.3_1_1", "Principle3.Guideline3_1.3_1_2", "Principle3.Guideline3_1.3_1_3", "Principle3.Guideline3_1.3_1_4", "Principle3.Guideline3_1.3_1_5", "Principle3.Guideline3_1.3_1_6", "Principle3.Guideline3_2.3_2_1", "Principle3.Guideline3_2.3_2_2", "Principle3.Guideline3_2.3_2_3", "Principle3.Guideline3_2.3_2_4", "Principle3.Guideline3_2.3_2_5", "Principle3.Guideline3_3.3_3_1", "Principle3.Guideline3_3.3_3_2", "Principle3.Guideline3_3.3_3_3", "Principle3.Guideline3_3.3_3_5", "Principle3.Guideline3_3.3_3_6", "Principle4.Guideline4_1.4_1_1", "Principle4.Guideline4_1.4_1_2"],
        getMsgInfo: function(a) {
            for (var b = {
                    Principle1: {
                        name: "Perceivable",
                        link: "http://www.w3.org/TR/WCAG20/#perceivable"
                    },
                    Principle2: {
                        name: "Operable",
                        link: "http://www.w3.org/TR/WCAG20/#operable"
                    },
                    Principle3: {
                        name: "Understandable",
                        link: "http://www.w3.org/TR/WCAG20/#understandable"
                    },
                    Principle4: {
                        name: "Robust",
                        link: "http://www.w3.org/TR/WCAG20/#robust"
                    }
                }, c = {
                    CR2: {
                        name: "Full pages",
                        landmark: "cc2",
                        priority: 0
                    },
                    CR3: {
                        name: "Complete processes",
                        landmark: "cc3",
                        priority: 0
                    },
                    CR4: {
                        name: "Only Accessibility-Supported Ways of Using Technologies",
                        landmark: "cc4",
                        priority: 0
                    },
                    CR5: {
                        name: "Non-Interference",
                        landmark: "cc5",
                        priority: 0
                    },
                    "1.1.1": {
                        name: "Non-Text Content",
                        landmark: "text-equiv-all",
                        priority: 1
                    },
                    "1.2.1": {
                        name: "Audio-only and Video-only (Prerecorded)",
                        landmark: "media-equiv-av-only-alt",
                        priority: 1
                    },
                    "1.2.2": {
                        name: "Captions (Prerecorded)",
                        landmark: "media-equiv-captions",
                        priority: 1
                    },
                    "1.2.3": {
                        name: "Audio Description or Media Alternative (Prerecorded)",
                        landmark: "media-equiv-audio-desc",
                        priority: 1
                    },
                    "1.2.4": {
                        name: "Captions (Live)",
                        landmark: "media-equiv-captions",
                        priority: 2
                    },
                    "1.2.5": {
                        name: "Audio Description (Prerecorded)",
                        landmark: "media-equiv-audio-desc",
                        priority: 2
                    },
                    "1.2.6": {
                        name: "Sign Language (Prerecorded)",
                        landmark: "media-equiv-sign",
                        priority: 3
                    },
                    "1.2.7": {
                        name: "Extended Audio Description (Prerecorded)",
                        landmark: "media-equiv-extended-ad",
                        priority: 3
                    },
                    "1.2.8": {
                        name: "Media Alternative (Prerecorded)",
                        landmark: "media-equiv-text-doc",
                        priority: 3
                    },
                    "1.2.9": {
                        name: "Audio-only (Live)",
                        landmark: "media-equiv-live-audio-only",
                        priority: 3
                    },
                    "1.3.1": {
                        name: "Info and Relationships",
                        landmark: "content-structure-separation-programmatic",
                        priority: 1
                    },
                    "1.3.2": {
                        name: "Meaningful Sequence",
                        landmark: "content-structure-separation-sequence",
                        priority: 1
                    },
                    "1.3.3": {
                        name: "Sensory Characteristics",
                        landmark: "content-structure-separation-understanding",
                        priority: 1
                    },
                    "1.4.1": {
                        name: "Use of Colour",
                        landmark: "visual-audio-contrast-without-color",
                        priority: 1
                    },
                    "1.4.2": {
                        name: "Audio Control",
                        landmark: "visual-audio-contrast-dis-audio",
                        priority: 1
                    },
                    "1.4.3": {
                        name: "Contrast (Minimum)",
                        landmark: "visual-audio-contrast-contrast",
                        priority: 1
                    },
                    "1.4.4": {
                        name: "Resize Text",
                        landmark: "visual-audio-contrast-scale",
                        priority: 1
                    },
                    "1.4.5": {
                        name: "Images of Text",
                        landmark: "visual-audio-contrast-text-presentation",
                        priority: 1
                    },
                    "1.4.6": {
                        name: "Contrast (Enhanced)",
                        landmark: "visual-audio-contrast7",
                        priority: 3
                    },
                    "1.4.7": {
                        name: "Low or No Background Audio",
                        landmark: "visual-audio-contrast-noaudio",
                        priority: 3
                    },
                    "1.4.8": {
                        name: "Visual Presentation",
                        landmark: "visual-audio-contrast-visual-presentation",
                        priority: 3
                    },
                    "1.4.9": {
                        name: "Images of Text (No Exception)",
                        landmark: "visual-audio-contrast-text-images",
                        priority: 3
                    },
                    "2.1.1": {
                        name: "Keyboard",
                        landmark: "keyboard-operation-keyboard-operable",
                        priority: 1
                    },
                    "2.1.2": {
                        name: "No Keyboard Trap",
                        landmark: "keyboard-operation-trapping",
                        priority: 1
                    },
                    "2.1.3": {
                        name: "Keyboard (No Exception)",
                        landmark: "keyboard-operation-all-funcs",
                        priority: 3
                    },
                    "2.2.1": {
                        name: "Timing Adjustable",
                        landmark: "time-limits-required-behaviors",
                        priority: 1
                    },
                    "2.2.2": {
                        name: "Pause, Stop, Hide",
                        landmark: "time-limits-pause",
                        priority: 1
                    },
                    "2.2.3": {
                        name: "No Timing",
                        landmark: "time-limits-no-exceptions",
                        priority: 3
                    },
                    "2.2.4": {
                        name: "Interruptions",
                        landmark: "time-limits-postponed",
                        priority: 3
                    },
                    "2.2.5": {
                        name: "Re-authenticating",
                        landmark: "time-limits-server-timeout",
                        priority: 3
                    },
                    "2.3.1": {
                        name: "Three Flashes or Below Threshold",
                        landmark: "seizure-does-not-violate",
                        priority: 1
                    },
                    "2.3.2": {
                        name: "Three Flashes",
                        landmark: "seizure-three-times",
                        priority: 3
                    },
                    "2.4.1": {
                        name: "Bypass Blocks",
                        landmark: "navigation-mechanisms-skip",
                        priority: 1
                    },
                    "2.4.2": {
                        name: "Page Titled",
                        landmark: "navigation-mechanisms-title",
                        priority: 1
                    },
                    "2.4.3": {
                        name: "Focus Order",
                        landmark: "navigation-mechanisms-focus-order",
                        priority: 1
                    },
                    "2.4.4": {
                        name: "Link Purpose (In Context)",
                        landmark: "navigation-mechanisms-refs",
                        priority: 1
                    },
                    "2.4.5": {
                        name: "Multiple Ways",
                        landmark: "navigation-mechanisms-mult-loc",
                        priority: 2
                    },
                    "2.4.6": {
                        name: "Headings and Labels",
                        landmark: "navigation-mechanisms-descriptive",
                        priority: 2
                    },
                    "2.4.7": {
                        name: "Focus Visible",
                        landmark: "navigation-mechanisms-focus-visible",
                        priority: 2
                    },
                    "2.4.8": {
                        name: "Location",
                        landmark: "navigation-mechanisms-location",
                        priority: 3
                    },
                    "2.4.9": {
                        name: "Link Purpose (Link Only)",
                        landmark: "navigation-mechanisms-link",
                        priority: 3
                    },
                    "2.4.10": {
                        name: "Section Headings",
                        landmark: "navigation-mechanisms-headings",
                        priority: 3
                    },
                    "3.1.1": {
                        name: "Language of Page",
                        landmark: "meaning-doc-lang-id",
                        priority: 1
                    },
                    "3.1.2": {
                        name: "Language of Parts",
                        landmark: "meaning-other-lang-id",
                        priority: 2
                    },
                    "3.1.3": {
                        name: "Unusual Words",
                        landmark: "meaning-idioms",
                        priority: 3
                    },
                    "3.1.4": {
                        name: "Abbreviations",
                        landmark: "meaning-located",
                        priority: 3
                    },
                    "3.1.5": {
                        name: "Reading Level",
                        landmark: "meaning-supplements",
                        priority: 3
                    },
                    "3.1.6": {
                        name: "Pronunciation",
                        landmark: "meaning-pronunciation",
                        priority: 3
                    },
                    "3.2.1": {
                        name: "On Focus",
                        landmark: "consistent-behavior-receive-focus",
                        priority: 1
                    },
                    "3.2.2": {
                        name: "On Input",
                        landmark: "consistent-behavior-unpredictable-change",
                        priority: 1
                    },
                    "3.2.3": {
                        name: "Consistent Navigation",
                        landmark: "consistent-behavior-consistent-locations",
                        priority: 2
                    },
                    "3.2.4": {
                        name: "Consistent Navigation",
                        landmark: "consistent-behavior-consistent-functionality",
                        priority: 2
                    },
                    "3.2.5": {
                        name: "Change on Request",
                        landmark: "consistent-behavior-no-extreme-changes-context",
                        priority: 3
                    },
                    "3.3.1": {
                        name: "Error Identification",
                        landmark: "minimize-error-identified",
                        priority: 1
                    },
                    "3.3.2": {
                        name: "Labels or Instructions",
                        landmark: "minimize-error-cues",
                        priority: 1
                    },
                    "3.3.3": {
                        name: "Error Suggestion",
                        landmark: "minimize-error-suggestions",
                        priority: 2
                    },
                    "3.3.4": {
                        name: "Error Prevention (Legal, Financial, Data)",
                        landmark: "minimize-error-reversible",
                        priority: 2
                    },
                    "3.3.5": {
                        name: "Help",
                        landmark: "minimize-error-context-help",
                        priority: 3
                    },
                    "3.3.6": {
                        name: "Error Prevention (All)",
                        landmark: "minimize-error-reversible-all",
                        priority: 3
                    },
                    "4.1.1": {
                        name: "Parsing",
                        landmark: "ensure-compat-parses",
                        priority: 1
                    },
                    "4.1.2": {
                        name: "Name, Role, Value",
                        landmark: "ensure-compat-rsv",
                        priority: 1
                    }
                }, d = a.split(".", 5), e = d[1], f = d[3].split("_").slice(0, 3).join("."), g = d[4].split(","), h = [], i = 0; i < g.length; i++) g[i] = g[i].split("."), h.push('<a href="http://www.w3.org/TR/WCAG20-TECHS/' + g[i][0] + '" target="_blank">' + g[i][0] + "</a>");
            var j = ['<a href="http://www.w3.org/TR/WCAG20/#' + c[f].landmark, '" target="_blank">', f, ": ", c[f].name, "</a>"].join("");
            ['<a href="', b[e].link, '" target="_blank">', b[e].name, "</a>"].join("");
            return [
                [_global.HTMLCS.getTranslation("auditor_success_criterion"), j],
                [_global.HTMLCS.getTranslation("auditor_suggested_techniques"), h.join(" ")]
            ]
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_1_1_1_1 = {
        register: function() {
            return ["_top", "img"]
        },
        process: function(a, b) {
            if (a === b) this.addNullAltTextResults(b), this.addMediaAlternativesResults(b);
            else {
                switch (a.nodeName.toLowerCase()) {
                    case "img":
                        this.testLinkStutter(a), this.testLongdesc(a)
                }
            }
        },
        addNullAltTextResults: function(a) {
            for (var b = this.testNullAltText(a), c = 0; c < b.img.emptyAltInLink.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.img.emptyAltInLink[c], _global.HTMLCS.getTranslation("1_1_1_H30.2"), "H30.2");
            for (var c = 0; c < b.img.nullAltWithTitle.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.img.nullAltWithTitle[c], _global.HTMLCS.getTranslation("1_1_1_H67.1"), "H67.1");
            for (var c = 0; c < b.img.ignored.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, b.img.ignored[c], _global.HTMLCS.getTranslation("1_1_1_H67.2"), "H67.2");
            for (var c = 0; c < b.img.missingAlt.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.img.missingAlt[c], _global.HTMLCS.getTranslation("1_1_1_H37"), "H37");
            for (var c = 0; c < b.img.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.img.generalAlt[c], _global.HTMLCS.getTranslation("1_1_1_G94.Image"), "G94.Image");
            for (var c = 0; c < b.inputImage.missingAlt.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.inputImage.missingAlt[c], _global.HTMLCS.getTranslation("1_1_1_H36"), "H36");
            for (var c = 0; c < b.inputImage.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.inputImage.generalAlt[c], _global.HTMLCS.getTranslation("1_1_1_G94.Button"), "G94.Button");
            for (var c = 0; c < b.area.missingAlt.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.area.missingAlt[c], _global.HTMLCS.getTranslation("1_1_1_H24"), "H24");
            for (var c = 0; c < b.area.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.area.generalAlt[c], _global.HTMLCS.getTranslation("1_1_1_H24.2"), "H24.2")
        },
        testNullAltText: function(a) {
            var b = {
                img: {
                    generalAlt: [],
                    missingAlt: [],
                    ignored: [],
                    nullAltWithTitle: [],
                    emptyAltInLink: []
                },
                inputImage: {
                    generalAlt: [],
                    missingAlt: []
                },
                area: {
                    generalAlt: [],
                    missingAlt: []
                }
            };
            elements = HTMLCS.util.getAllElements(a, 'img, area, input[type="image"]');
            for (var c = 0; c < elements.length; c++) {
                var d = elements[c],
                    e = d.nodeName.toLowerCase(),
                    f = !1,
                    g = !1,
                    h = !1;
                if ("a" === d.parentNode.nodeName.toLowerCase()) {
                    var i = HTMLCS.util.getPreviousSiblingElement(d, null),
                        j = HTMLCS.util.getNextSiblingElement(d, null);
                    if (null === i && null === j) {
                        var k = d.parentNode.textContent;
                        if (void 0 !== d.parentNode.textContent) var k = d.parentNode.textContent;
                        else var k = d.parentNode.innerText;
                        !0 === HTMLCS.util.isStringEmpty(k) && (f = !0)
                    }
                }
                switch (!1 === d.hasAttribute("alt") ? g = !0 : d.getAttribute("alt") && !0 !== HTMLCS.util.isStringEmpty(d.getAttribute("alt")) || (h = !0), e) {
                    case "img":
                        !0 !== f || !0 !== g && !0 !== h ? !0 === g ? b.img.missingAlt.push(d) : !0 === h ? !0 === d.hasAttribute("title") && !1 === HTMLCS.util.isStringEmpty(d.getAttribute("title")) ? b.img.nullAltWithTitle.push(d) : b.img.ignored.push(d) : b.img.generalAlt.push(d) : b.img.emptyAltInLink.push(d.parentNode);
                        break;
                    case "input":
                        !0 === g || !0 === h ? b.inputImage.missingAlt.push(d) : b.inputImage.generalAlt.push(d);
                        break;
                    case "area":
                        !0 === g || !0 === h ? b.area.missingAlt.push(d) : b.inputImage.generalAlt.push(d)
                }
            }
            return b
        },
        testLongdesc: function(a) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_1_1_G73,G74"), "G73,G74")
        },
        testLinkStutter: function(a) {
            if ("a" === a.parentNode.nodeName.toLowerCase()) {
                var b = a.parentNode,
                    c = {
                        anchor: {
                            href: b.getAttribute("href"),
                            text: HTMLCS.util.getElementTextContent(b, !1),
                            alt: this._getLinkAltText(b)
                        }
                    };
                if (null === c.anchor.alt && (c.anchor.alt = ""), null !== c.anchor.alt && "" !== c.anchor.alt && HTMLCS.util.trim(c.anchor.alt).toLowerCase() === HTMLCS.util.trim(c.anchor.text).toLowerCase() && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_1_1_H2.EG5"), "H2.EG5"), "" === c.anchor.text) {
                    var d = HTMLCS.util.getPreviousSiblingElement(b, "a", !0),
                        e = HTMLCS.util.getNextSiblingElement(b, "a", !0);
                    null !== d && (c.previous = {
                        href: d.getAttribute("href"),
                        text: HTMLCS.util.getElementTextContent(d, !1),
                        alt: this._getLinkAltText(d)
                    }, null === c.previous.alt && (c.previous.alt = "")), null !== e && (c.next = {
                        href: e.getAttribute("href"),
                        text: HTMLCS.util.getElementTextContent(e, !1),
                        alt: this._getLinkAltText(e)
                    }, null === c.next.alt && (c.next.alt = "")), c.next && "" !== c.next.href && null !== c.next.href && c.anchor.href === c.next.href && ("" !== c.next.text && "" === c.anchor.alt ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_1_1_H2.EG4"), "H2.EG4") : c.next.text.toLowerCase() === c.anchor.alt.toLowerCase() && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_1_1_H2.EG3"), "H2.EG3")), c.previous && "" !== c.previous.href && null !== c.previous.href && c.anchor.href === c.previous.href && ("" !== c.previous.text && "" === c.anchor.alt ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_1_1_H2.EG4"), "H2.EG4") : c.previous.text.toLowerCase() === c.anchor.alt.toLowerCase() && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_1_1_H2.EG3")))
                }
            }
        },
        addMediaAlternativesResults: function(a) {
            for (var b = this.testMediaTextAlternatives(a), c = 0; c < b.object.missingBody.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.object.missingBody[c], _global.HTMLCS.getTranslation("1_1_1_H53,ARIA6"), "H53,ARIA6");
            for (var c = 0; c < b.object.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.object.generalAlt[c], _global.HTMLCS.getTranslation("1_1_1_G94,G92.Object,ARIA6"), "G94,G92.Object,ARIA6");
            for (var c = 0; c < b.applet.missingBody.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.applet.missingBody[c], _global.HTMLCS.getTranslation("1_1_1_H35.3"), "H35.3");
            for (var c = 0; c < b.applet.missingAlt.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.applet.missingAlt[c], _global.HTMLCS.getTranslation("1_1_1_H35.2"), "H35.2");
            for (var c = 0; c < b.applet.generalAlt.length; c++) HTMLCS.addMessage(HTMLCS.NOTICE, b.applet.generalAlt[c], _global.HTMLCS.getTranslation("1_1_1_G94,G92.Applet"), "G94,G92.Applet")
        },
        testMediaTextAlternatives: function(a) {
            for (var b = {
                    object: {
                        missingBody: [],
                        generalAlt: []
                    },
                    applet: {
                        missingBody: [],
                        missingAlt: [],
                        generalAlt: []
                    }
                }, c = HTMLCS.util.getAllElements(a, "object"), d = 0; d < c.length; d++) {
                var e = c[d],
                    f = (e.nodeName.toLowerCase(), e.querySelector("object"));
                null === f && (!0 === HTMLCS.util.isStringEmpty(HTMLCS.util.getElementTextContent(e, !0)) ? !1 === HTMLCS.util.hasValidAriaLabel(e) && b.object.missingBody.push(e) : !1 === HTMLCS.util.hasValidAriaLabel(e) && b.object.generalAlt.push(e))
            }
            for (var c = HTMLCS.util.getAllElements(a, "applet"), d = 0; d < c.length; d++) {
                var f = e.querySelector("object"),
                    g = !1;
                if (null === f) {
                    var h = HTMLCS.util.getElementTextContent(e, !0);
                    !0 === HTMLCS.util.isStringEmpty(h) && (b.applet.missingBody.push(e), g = !0)
                }
                var i = e.getAttribute("alt") || "";
                !0 === HTMLCS.util.isStringEmpty(i) && (b.applet.missingAlt.push(e), g = !0), !0 === HTMLCS.util.hasValidAriaLabel(e) && (g = !1), !1 === g && b.applet.generalAlt.push(e)
            }
            return b
        },
        _getLinkAltText: function(a) {
            for (var a = a.cloneNode(!0), b = [], c = 0; c < a.childNodes.length; c++) b.push(a.childNodes[c]);
            for (var d = null; b.length > 0;) {
                var e = b.shift();
                if (1 === e.nodeType && "img" === e.nodeName.toLowerCase() && !0 === e.hasAttribute("alt")) {
                    d = e.getAttribute("alt"), d = d ? d.replace(/^\s+|\s+$/g, "") : "";
                    break
                }
            }
            return d
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_2_1_2_1 = {
        register: function() {
            return ["object", "embed", "applet", "bgsound", "audio", "video"]
        },
        process: function(a, b) {
            var c = a.nodeName.toLowerCase();
            "video" !== c && HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_1_G158"), "G158"), "bgsound" !== c && "audio" !== c && HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_1_G159,G166"), "G159,G166")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_2_1_2_2 = {
        register: function() {
            return ["object", "embed", "applet", "video"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_2_G87,G93"), "G87,G93")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_2_1_2_3 = {
        register: function() {
            return ["object", "embed", "applet", "video"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_3_G69,G78,G173,G8"), "G69,G78,G173,G8")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_2_1_2_4 = {
        register: function() {
            return ["object", "embed", "applet", "video"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_4_G9,G87,G93"), "G9,G87,G93")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_2_1_2_5 = {
        register: function() {
            return ["object", "embed", "applet", "video"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_5_G78,G173,G8"), "G78,G173,G8")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_2_1_2_6 = {
        register: function() {
            return ["object", "embed", "applet", "video"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_6_G54,G81"), "G54,G81")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_2_1_2_7 = {
        register: function() {
            return ["object", "embed", "applet", "video"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_7_G8"), "G8")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_2_1_2_8 = {
        register: function() {
            return ["object", "embed", "applet", "video"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_8_G69,G159"), "G69,G159")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_2_1_2_9 = {
        register: function() {
            return ["object", "embed", "applet", "bgsound", "audio"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_2_9_G150,G151,G157"), "G150,G151,G157")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_1_A = {
        _labelNames: null,
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            var c = HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_1;
            a === b && c.testHeadingOrder(b, HTMLCS.WARNING)
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_1_AAA = {
        _labelNames: null,
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            var c = HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_1;
            a === b && c.testHeadingOrder(b, HTMLCS.ERROR)
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_1 = {
        _labelNames: null,
        register: function() {
            return ["_top", "p", "div", "input", "select", "textarea", "button", "table", "fieldset", "form", "h1", "h2", "h3", "h4", "h5", "h6"]
        },
        process: function(a, b) {
            var c = a.nodeName.toLowerCase();
            if (a === b) this.testPresentationMarkup(b), this.testEmptyDupeLabelForAttrs(b);
            else switch (c) {
                case "input":
                case "textarea":
                case "button":
                    this.testLabelsOnInputs(a, b);
                    break;
                case "form":
                    this.testRequiredFieldsets(a);
                    break;
                case "select":
                    this.testLabelsOnInputs(a, b), this.testOptgroup(a);
                    break;
                case "p":
                case "div":
                    this.testNonSemanticHeading(a), this.testListsWithBreaks(a), this.testUnstructuredNavLinks(a);
                    break;
                case "table":
                    this.testGeneralTable(a), this.testTableHeaders(a), this.testTableCaptionSummary(a);
                    break;
                case "fieldset":
                    this.testFieldsetLegend(a);
                    break;
                case "h1":
                case "h2":
                case "h3":
                case "h4":
                case "h5":
                case "h6":
                    this.testEmptyHeading(a)
            }
        },
        testSemanticPresentationRole: function(a) {
            if (!1 === HTMLCS.util.isAriaHidden(a) && a.hasAttribute("role") && "presentation" === a.getAttribute("role")) {
                var b = ["div", "span", "b", "i"],
                    c = a.querySelectorAll("*:not(" + b.join("):not(") + ")");
                c = [].filter.call(c, function(a) {
                    return !1 === a.hasAttribute("role")
                }), c.length && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_F92,ARIA4"), "F92,ARIA4")
            }
        },
        testEmptyDupeLabelForAttrs: function(a) {
            this._labelNames = {};
            for (var b = a.getElementsByTagName("label"), c = 0; c < b.length; c++)
                if (null !== b[c].getAttribute("for") && "" !== b[c].getAttribute("for")) {
                    var d = b[c].getAttribute("for");
                    if (this._labelNames[d] && null !== this._labelNames[d]) this._labelNames[d] = null;
                    else {
                        if (this._labelNames[d] = b[c], a.ownerDocument) var e = a.ownerDocument.getElementById(d);
                        else var e = a.getElementById(d);
                        if (null === e) {
                            var f = HTMLCS.ERROR,
                                g = _global.HTMLCS.getTranslation("1_3_1_H44.NonExistent"),
                                h = "H44.NonExistent";
                            if (!0 === HTMLCS.isFullDoc(a) || "body" === a.nodeName.toLowerCase()) {
                                f = HTMLCS.WARNING, g = _global.HTMLCS.getTranslation("1_3_1_H44.NonExistentFragment");
                                var h = "H44.NonExistentFragment"
                            }
                            HTMLCS.addMessage(f, b[c], g, h)
                        } else {
                            var i = e.nodeName.toLowerCase(); - 1 === "input|select|textarea|button|keygen|meter|output|progress".indexOf(i) && HTMLCS.addMessage(HTMLCS.WARNING, b[c], _global.HTMLCS.getTranslation("1_3_1_H44.NotFormControl"), "H44.NotFormControl")
                        }
                    }
                }
        },
        testLabelsOnInputs: function(a, b, c) {
            var d = a.nodeName.toLowerCase(),
                e = d;
            "input" === e && (e = !0 === a.hasAttribute("type") ? a.getAttribute("type") : "text");
            var f = !1,
                g = function(a) {
                    f || (f = {}), f[a] = !0
                },
                h = !1,
                e = e.toLowerCase();
            "select" === e || "textarea" === e ? h = !0 : !0 === /^(radio|checkbox|text|file|password)$/.test(e) && (h = !0), null !== a.getAttribute("hidden") && (h = !1), a.ownerDocument.querySelector('label[for="' + a.id + '"]') && g("explicit");
            var i = !1;
            a.parentNode && HTMLCS.util.eachParentNode(a, function(a) {
                "label" === a.nodeName.toLowerCase() && (i = !0)
            }), !0 === i && g("implicit");
            var j = a.getAttribute("title");
            return null !== j && (!0 === /^\s*$/.test(j) && !0 === h ? HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H65"), "H65") : g("title")), !0 === a.hasAttribute("aria-label") && (!1 === HTMLCS.util.hasValidAriaLabel(a) ? HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_ARIA6"), "ARIA6") : g("aria-label")), !0 === a.hasAttribute("aria-labelledby") && (!1 === HTMLCS.util.hasValidAriaLabel(a) ? HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_ARIA16,ARIA9").replace(/\{\{id\}\}/g, a.getAttribute("aria-labelledby")), "ARIA16,ARIA9") : g("aria-labelledby")), !0 !== c && (!1 !== f && !1 === h ? "hidden" === e ? HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_F68.Hidden"), "F68.Hidden") : null !== a.getAttribute("hidden") && HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_F68.HiddenAttr"), "F68.HiddenAttr") : !1 === f && !0 === h && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_F68"), "F68")), f
        },
        testPresentationMarkup: function(a) {
            var b = HTMLCS.util.getElementWindow(a).document,
                c = HTMLCS.util.getDocumentType(b);
            if (!c || "html5" !== c && "xhtml5" !== c) {
                for (var d = HTMLCS.util.getAllElements(a, "b, i, u, s, strike, tt, big, small, center, font"), e = 0; e < d.length; e++) {
                    var f = "H49." + d[e].nodeName.substr(0, 1).toUpperCase() + d[e].nodeName.substr(1).toLowerCase();
                    HTMLCS.addMessage(HTMLCS.WARNING, d[e], _global.HTMLCS.getTranslation("1_3_1_H49.Semantic"), f)
                }
                for (var d = HTMLCS.util.getAllElements(a, "*[align]"), e = 0; e < d.length; e++) {
                    var f = "H49.AlignAttr";
                    HTMLCS.addMessage(HTMLCS.WARNING, d[e], _global.HTMLCS.getTranslation("1_3_1_H49.AlignAttr.Semantic"), f)
                }
            } else {
                for (var d = HTMLCS.util.getAllElements(a, "strike, tt, big, center, font"), e = 0; e < d.length; e++) {
                    var f = "H49." + d[e].nodeName.substr(0, 1).toUpperCase() + d[e].nodeName.substr(1).toLowerCase();
                    HTMLCS.addMessage(HTMLCS.ERROR, d[e], _global.HTMLCS.getTranslation("1_3_1_H49."), f)
                }
                for (var d = HTMLCS.util.getAllElements(a, "*[align]"), e = 0; e < d.length; e++) {
                    var f = "H49.AlignAttr";
                    HTMLCS.addMessage(HTMLCS.ERROR, d[e], "Align attributes .", f)
                }
            }
        },
        testNonSemanticHeading: function(a) {
            var b = a.nodeName.toLowerCase();
            if ("p" === b || "div" === b) {
                var c = a.childNodes;
                if (1 === c.length && 1 === c[0].nodeType) {
                    !0 === /^(strong|em|b|i|u)$/.test(c[0].nodeName.toLowerCase()) && HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H42"), "H42")
                }
            }
        },
        testTableHeaders: function(a) {
            for (var b = HTMLCS.util.testTableHeaders(a), c = this._testTableScopeAttrs(a), d = 0; d < c.invalid.length; d++) HTMLCS.addMessage(HTMLCS.ERROR, c.invalid[d], _global.HTMLCS.getTranslation("1_3_1_H63.3"), "H63.3");
            for (var d = 0; d < c.obsoleteTd.length; d++) HTMLCS.addMessage(HTMLCS.WARNING, c.obsoleteTd[d], _global.HTMLCS.getTranslation("1_3_1_H63.2"), "H63.2");
            !0 === b.allowScope ? 0 === c.missing.length && b.required : !0 === c.used && (HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H43.ScopeAmbiguous"), "H43.ScopeAmbiguous"), c = null);
            for (var d = 0; d < b.wrongHeaders.length; d++) HTMLCS.addMessage(HTMLCS.ERROR, b.wrongHeaders[d].element, _global.HTMLCS.getTranslation("1_3_1_H43.IncorrectAttr").replace(/\{\{expected\}\}/g, b.wrongHeaders[d].expected).replace(/\{\{actual\}\}/g, b.wrongHeaders[d].actual), "H43.IncorrectAttr");
            !0 === b.required && !1 === b.allowScope && (!1 === b.used ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H43.HeadersRequired"), "H43.HeadersRequired") : (b.missingThId.length > 0 && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H43.MissingHeaderIds"), "H43.MissingHeaderIds"),
                b.missingTd.length > 0 && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H43.MissingHeadersAttrs"), "H43.MissingHeadersAttrs"))), !0 === b.required && !0 === b.allowScope && !1 === b.correct && !1 === c.correct && (!1 === c.used && !1 === b.used ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H43,H63"), "H43,H63") : !1 === c.used && (b.missingThId.length > 0 || b.missingTd.length > 0) ? (b.missingThId.length > 0 && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H43.MissingHeaderIds"), "H43.MissingHeaderIds"), b.missingTd.length > 0 && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H43.MissingHeadersAttrs"), "H43.MissingHeadersAttrs")) : c.missing.length > 0 && !1 === b.used ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H63.1"), "H63.1") : c.missing.length > 0 && (b.missingThId.length > 0 || b.missingTd.length > 0) && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H43,H63"), "H43,H63"))
        },
        _testTableScopeAttrs: function(a) {
            var b = {
                    th: a.getElementsByTagName("th"),
                    td: a.getElementsByTagName("td")
                },
                c = {
                    used: !1,
                    correct: !0,
                    missing: [],
                    invalid: [],
                    obsoleteTd: []
                };
            for (var d in b)
                for (var e = 0; e < b[d].length; e++) {
                    var f = b[d][e],
                        g = "";
                    !0 === f.hasAttribute("scope") && (c.used = !0, f.getAttribute("scope") && (g = f.getAttribute("scope"))), "th" === f.nodeName.toLowerCase() ? !0 === /^\s*$/.test(g) ? (c.correct = !1, c.missing.push(f)) : !1 === /^(row|col|rowgroup|colgroup)$/.test(g) && (c.correct = !1, c.invalid.push(f)) : "" !== g && (c.obsoleteTd.push(f), !1 === /^(row|col|rowgroup|colgroup)$/.test(g) && (c.correct = !1, c.invalid.push(f)))
                }
            return c
        },
        testTableCaptionSummary: function(a) {
            var b = a.getAttribute("summary") || "",
                c = a.getElementsByTagName("caption"),
                d = "";
            c.length > 0 && (d = c[0].innerHTML.replace(/^\s*(.*?)\s*$/g, "$1"));
            var e = HTMLCS.util.getDocumentType(a.ownerDocument);
            e && -1 === e.indexOf("html5") && (b = b.replace(/^\s*(.*?)\s*$/g, "$1"), "" !== b ? !0 === HTMLCS.util.isLayoutTable(a) ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H73.3.LayoutTable"), "H73.3.LayoutTable") : (d === b && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H39,H73.4"), "H39,H73.4"), HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_3_1_H73.3.Check"), "H73.3.Check")) : !1 === HTMLCS.util.isLayoutTable(a) && HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H73.3.NoSummary"), "H73.3.NoSummary")), "" !== d ? !0 === HTMLCS.util.isLayoutTable(a) ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H39.3.LayoutTable"), "H39.3.LayoutTable") : HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_3_1_H39.3.Check"), "H39.3.Check") : !1 === HTMLCS.util.isLayoutTable(a) && HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H39.3.NoCaption"), "H39.3.NoCaption")
        },
        testFieldsetLegend: function(a) {
            var b = a.querySelector("legend");
            null !== b && b.parentNode === a || HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H71.NoLegend"), "H71.NoLegend")
        },
        testOptgroup: function(a) {
            null === a.querySelector("optgroup") && HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H85.2"), "H85.2")
        },
        testRequiredFieldsets: function(a) {
            for (var b = a.querySelectorAll("input[type=radio], input[type=checkbox]"), c = {}, d = 0; d < b.length; d++) {
                var e = b[d];
                if (!0 === e.hasAttribute("name")) {
                    for (var f = e.getAttribute("name"), g = e.parentNode;
                        "fieldset" !== g.nodeName.toLowerCase() && null !== g && g !== a;) g = g.parentNode;
                    "fieldset" !== g.nodeName.toLowerCase() && (g = null)
                }
                if (void 0 === c[f]) c[f] = g;
                else if (null === g || g !== c[f]) {
                    HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H71.SameName"), "H71.SameName");
                    break
                }
            }
        },
        testListsWithBreaks: function(a) {
            var b = a.querySelector("br"),
                c = [];
            if (null !== b) {
                for (var d = [], e = 0; e < a.childNodes.length; e++) d.push(a.childNodes[e]);
                for (var f = []; d.length > 0;) {
                    var g = d.shift();
                    if (1 === g.nodeType)
                        if ("br" === g.nodeName.toLowerCase()) c.push(f.join(" ").replace(/^\s*(.*?)\s*$/g, "$1")), f = [];
                        else
                            for (var e = g.childNodes.length - 1; e >= 0; --e) d.unshift(g.childNodes[e]);
                    else 3 === g.nodeType && f.push(g.nodeValue)
                }
                f.length > 0 && c.push(f.join(" ").replace(/^\s*(.*?)\s*$/g, "$1"));
                for (var e = 0; e < c.length; e++) {
                    if (!0 === /^[\-*]\s+/.test(c[0])) {
                        HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H48.1"), "H48.1");
                        break
                    }
                    if (!0 === /^\d+[:\/\-.]?\s+/.test(c[0])) {
                        HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H48.2"), "H48.2");
                        break
                    }
                }
            }
        },
        testHeadingOrder: function(a, b) {
            for (var c = 1, d = HTMLCS.util.getAllElements(a, "h2, h3, h4, h5, h6"), e = 0; e < d.length; e++) {
                var f = parseInt(d[e].nodeName.substr(1, 1));
                if (f - c > 1) {
                    1 === HTMLCS.addMessage(b, d[e], _global.HTMLCS.getTranslation("1_3_1_G141_b").replace(/\{\{headingNum\}\}/g, f).replace(/\{\{properHeadingNum\}\}/g, c + 1), "G141")
                }
                c = f
            }
        },
        testEmptyHeading: function(a) {
            !0 === /^\s*$/.test(HTMLCS.util.getElementTextContent(a, !0)) && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_3_1_H42.2"), "H42.2")
        },
        testUnstructuredNavLinks: function(a) {
            for (var b = (a.nodeName.toLowerCase(), 0), c = a.childNodes, d = 0; d < c.length && !(1 === c[d].nodeType && "a" === c[d].nodeName.toLowerCase() && ++b > 1); d++);
            if (b > 1) {
                for (var e = a.parentNode; null !== e && "ul" !== e.nodeName.toLowerCase() && "ol" !== e.nodeName.toLowerCase();) e = e.parentNode;
                null === e && HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_3_1_H48"), "H48")
            }
        },
        testGeneralTable: function(a) {
            !0 === HTMLCS.util.isLayoutTable(a) ? HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_3_1_LayoutTable"), "LayoutTable") : HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_3_1_DataTable"), "DataTable")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_2 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_3_2_G57"), "G57")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_3 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_3_3_G96"), "G96")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_1 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_1_G14,G18"), "G14,G182")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_2 = {
        register: function() {
            return ["object", "embed", "applet", "bgsound", "audio", "video"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_2_F23"), "F23")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_3_Contrast = {
        testContrastRatio: function(a, b, c) {
            var d = (new Date, []);
            if (a.ownerDocument) var e = [a];
            else {
                var e = [],
                    f = a.getElementsByTagName("body");
                if (f.length) var e = [f[0]]
            }
            for (; e.length > 0;) {
                var g = e.shift();
                if (g && 1 === g.nodeType && !1 === HTMLCS.util.isVisuallyHidden(g) && !1 === HTMLCS.util.isDisabled(g)) {
                    for (var h = !1, i = 0; i < g.childNodes.length; i++) 1 === g.childNodes[i].nodeType ? e.push(g.childNodes[i]) : 3 === g.childNodes[i].nodeType && "" !== HTMLCS.util.trim(g.childNodes[i].nodeValue) && (h = !0);
                    if (!0 === h) {
                        var j = HTMLCS.util.style(g);
                        if (j) {
                            var k = j.backgroundColor,
                                l = j.color,
                                m = !1,
                                n = !1;
                            "none" !== j.backgroundImage && (m = !0), "absolute" == j.position && (n = !0);
                            var o = g.parentNode,
                                p = .75 * parseFloat(j.fontSize, 10),
                                q = 18;
                            if ("bold" === j.fontWeight || parseInt(j.fontWeight, 10) >= 600) var q = 14;
                            var r = b;
                            for (p >= q && (r = c);
                                ("transparent" === k || "rgba(0, 0, 0, 0)" === k) && o && o.ownerDocument;) {
                                var s = HTMLCS.util.style(o),
                                    k = s.backgroundColor;
                                "none" !== s.backgroundImage && (m = !0), "absolute" == s.position && (n = !0);
                                var t = HTMLCS.util.style(o, ":before");
                                if (t && "fixed" == t.position && "transform" == t.willChange && t.width == s.width && parseInt(t.height, 10) <= parseInt(s.height, 10) && "none" !== t.backgroundImage) {
                                    m = !0;
                                    break
                                }
                                o = o.parentNode
                            }
                            var u = HTMLCS.util.colourStrToRGB(k).alpha,
                                v = HTMLCS.util.colourStrToRGB(l).alpha;
                            if (k && u < 1 && u > 0) {
                                d.push({
                                    element: g,
                                    colour: l,
                                    bgColour: k,
                                    value: void 0,
                                    required: r,
                                    hasAlpha: !0
                                });
                                continue
                            }
                            if (l && v < 1 && v > 0) {
                                d.push({
                                    element: g,
                                    colour: l,
                                    bgColour: l,
                                    value: void 0,
                                    required: r,
                                    hasAlpha: !0
                                });
                                continue
                            }
                            if (!0 === m) {
                                d.push({
                                    element: g,
                                    colour: l,
                                    bgColour: void 0,
                                    value: void 0,
                                    required: r,
                                    hasBgImage: !0
                                });
                                continue
                            }
                            if (!0 === n) {
                                d.push({
                                    element: g,
                                    colour: l,
                                    bgColour: void 0,
                                    value: void 0,
                                    required: r,
                                    isAbsolute: !0
                                });
                                continue
                            }
                            if ("transparent" === k || "rgba(0, 0, 0, 0)" === k) continue;
                            var w = HTMLCS.util.contrastRatio(k, l);
                            if (w < r) {
                                var x = this.recommendColour(k, l, r);
                                d.push({
                                    element: g,
                                    colour: j.color,
                                    bgColour: k,
                                    value: w,
                                    required: r,
                                    recommendation: x
                                })
                            }
                        }
                    }
                }
            }
            return d
        },
        recommendColour: function(a, b, c) {
            var b = HTMLCS.util.RGBtoColourStr(HTMLCS.util.colourStrToRGB(b)),
                a = HTMLCS.util.RGBtoColourStr(HTMLCS.util.colourStrToRGB(a)),
                d = HTMLCS.util.contrastRatio(b, a),
                e = Math.abs(HTMLCS.util.relativeLum(b) - .5),
                f = Math.abs(HTMLCS.util.relativeLum(a) - .5),
                g = null;
            if (d < c) {
                var h = 1.0025;
                if (e <= f) {
                    var i = "back",
                        j = a;
                    if (HTMLCS.util.relativeLum(a) < .5) var h = 1 / h
                } else {
                    var i = "fore",
                        j = b;
                    if (HTMLCS.util.relativeLum(b) < .5) var h = 1 / h
                }
                for (var k = HTMLCS.util.sRGBtoHSV(j), l = (k.saturation, k.value, b), m = a, n = !1, o = 0; d < c;) {
                    if ("#fff" === j || "#000" === j)
                        if (!0 === n)
                            if ("fore" === i)
                                for (var p = m, q = 1; m === p;) {
                                    var m = this.multiplyColour(m, Math.pow(1 / h, q));
                                    q++
                                } else
                                    for (var r = l, q = 1; l === r;) {
                                        var l = this.multiplyColour(l, Math.pow(1 / h, q));
                                        q++
                                    } else {
                                        if (l = b, m = a, h = 1 / h, "fore" === i) {
                                            i = "back";
                                            var k = a
                                        } else {
                                            i = "fore";
                                            var k = b
                                        }
                                        k = HTMLCS.util.sRGBtoHSV(k), k.saturation * k.value, n = !0
                                    }
                    o++;
                    var j = HTMLCS.util.HSVtosRGB(k),
                        j = this.multiplyColour(j, Math.pow(h, o));
                    if ("fore" === i) var l = j;
                    else var m = j;
                    var d = HTMLCS.util.contrastRatio(l, m)
                }
                g = {
                    fore: {
                        from: b,
                        to: l
                    },
                    back: {
                        from: a,
                        to: m
                    }
                }
            }
            return g
        },
        multiplyColour: function(a, b) {
            var c = HTMLCS.util.sRGBtoHSV(a),
                d = c.saturation * c.value;
            return 0 === c.value && (c.value = 1 / 255), c.value = c.value * b, 0 === c.value ? c.saturation = 0 : c.saturation = d / c.value, c.value = Math.min(1, c.value), c.saturation = Math.min(1, c.saturation), HTMLCS.util.RGBtoColourStr(HTMLCS.util.HSVtosRGB(c))
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_3_F24 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            for (var c = HTMLCS.util.getAllElements(b, "*"), d = 0; d < c.length; d++) this.testColourComboFail(c[d])
        },
        testColourComboFail: function(a) {
            var b = a.hasAttribute("color");
            b = b || a.hasAttribute("link"), b = b || a.hasAttribute("vlink"), b = b || a.hasAttribute("alink");
            var c = a.hasAttribute("bgcolor");
            if (a.style) {
                var d = a.style.color,
                    e = a.style.background;
                "" !== d && "auto" !== d && (b = !0), "" !== e && "auto" !== e && (c = !0)
            }
            c !== b && (!0 === c ? HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_4_3_F24.BGColour"), "F24.BGColour") : HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_4_3_F24.FGColour"), "F24.FGColour"))
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_3 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            if (a === b)
                for (var c = HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_3_Contrast.testContrastRatio(b, 4.5, 3), d = 0; d < c.length; d++) {
                    for (var a = c[d].element, e = 2, f = Math.round(c[d].value * Math.pow(10, e)) / Math.pow(10, e), g = c[d].required, h = c[d].recommendation, i = c[d].hasBgImage || !1, j = (c[d].bgColour, c[d].isAbsolute || !1), k = c[d].hasAlpha || !1; g === f;) e++, f = Math.round(c[d].value * Math.pow(10, e)) / Math.pow(10, e);
                    if (4.5 === g) var l = "G18";
                    else if (3 === g) var l = "G145";
                    var m = [];
                    h && (h.fore.from !== h.fore.to && m.push(_global.HTMLCS.getTranslation("1_4_3_G18_or_G145.Fail.Recomendation.Text") + " " + h.fore.to), h.back.from !== h.back.to && m.push(_global.HTMLCS.getTranslation("1_4_3_G18_or_G145.Fail.Recomendation.Background") + " " + h.back.to)), m.length > 0 && (m = " " + _global.HTMLCS.getTranslation("1_4_3_G18_or_G145.Fail.Recomendation") + " " + m.join(", ") + "."), !0 === j ? (l += ".Abs", HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_4_3_G18_or_G145.Abs").replace(/\{\{required\}\}/g, g), l)) : !0 === i ? (l += ".BgImage", HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_4_3_G18_or_G145.BgImage").replace(/\{\{required\}\}/g, g), l)) : !0 === k ? (l += ".Alpha", HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_4_3_G18_or_G145.Alpha").replace(/\{\{required\}\}/g, g), l)) : (l += ".Fail", HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_4_3_G18_or_G145.Fail").replace(/\{\{required\}\}/g, g).replace(/\{\{value\}\}/g, f) + m, l))
                }
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_4 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_4_G142"), "G142")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_5 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            null !== b.querySelector("img") && HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_5_G140,C22,C30.AALevel"), "G140,C22,C30.AALevel")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_6 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            if (a === b)
                for (var c = HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_3_Contrast.testContrastRatio(b, 7, 4.5), d = 0; d < c.length; d++) {
                    for (var a = c[d].element, e = 2, f = Math.round(c[d].value * Math.pow(10, e)) / Math.pow(10, e), g = c[d].required, h = c[d].recommendation, i = c[d].hasBgImage || !1, j = c[d].isAbsolute || !1; g === f;) e++, f = Math.round(c[d].value * Math.pow(10, e)) / Math.pow(10, e);
                    if (4.5 === g) var k = "G18";
                    else if (7 === g) var k = "G17";
                    var l = [];
                    h && (h.fore.from !== h.fore.to && l.push(_global.HTMLCS.getTranslation("1_4_6_G18_or_G17.Fail.Recomendation.Text") + " " + h.fore.to), h.back.from !== h.back.to && l.push(_global.HTMLCS.getTranslation("1_4_6_G18_or_G17.Fail.Recomendation.Background") + h.back.to)), l.length > 0 && (l = " " + _global.HTMLCS.getTranslation("1_4_6_G18_or_G17.Fail.Recomendation") + " " + l.join(", ") + "."), !0 === j ? (k += ".Abs", HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_4_6_G18_or_G17.Abs").replace(/\{\{required\}\}/g, g), k)) : !0 === i ? (k += ".BgImage", HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("1_4_6_G18_or_G17.BgImage").replace(/\{\{required\}\}/g, g), k)) : (k += ".Fail", HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("1_4_6_G18_or_G17.Fail").replace(/\{\{required\}\}/g, g).replace(/\{\{value\}\}/g, f) + l, k))
                }
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_7 = {
        register: function() {
            return ["object", "embed", "applet", "bgsound", "audio"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("1_4_7_G56"), "G56")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_8 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_8_G148,G156,G175"), "G148,G156,G175"), HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_8_H87,C20"), "H87,C20"), HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_8_C19,G172,G169"), "C19,G172,G169"), HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_8_G188,C21"), "G188,C21"), HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_8_H87,G146,C26"), "H87,G146,C26")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_4_1_4_9 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            null !== b.querySelector("img") && HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("1_4_9_G140,C22,C30.NoException"), "G140,C22,C30.NoException")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_1_2_1_1 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            if (a === b) {
                HTMLCS.util.getAllElements(b, "*[onclick], *[onkeyup], *[onkeydown], *[onkeypress], *[onfocus], *[onblur]").forEach(function(a) {
                    !1 === HTMLCS.util.isKeyboardNavigable(a) && HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("2_1_1_G90"), "G90")
                });
                for (var c = HTMLCS.util.getAllElements(b, "*[ondblclick]"), d = 0; d < c.length; d++) HTMLCS.addMessage(HTMLCS.WARNING, c[d], _global.HTMLCS.getTranslation("2_1_1_SCR20.DblClick"), "SCR20.DblClick");
                for (var e = HTMLCS.util.getAllElements(b, "*[onmouseover]"), d = 0; d < e.length; d++) HTMLCS.addMessage(HTMLCS.WARNING, e[d], _global.HTMLCS.getTranslation("2_1_1_SCR20.MouseOver"), "SCR20.MouseOver");
                for (var f = HTMLCS.util.getAllElements(b, "*[onmouseout]"), d = 0; d < f.length; d++) HTMLCS.addMessage(HTMLCS.WARNING, f[d], _global.HTMLCS.getTranslation("2_1_1_SCR20.MouseOut"), "SCR20.MouseOut");
                for (var g = HTMLCS.util.getAllElements(b, "*[onmousemove]"), d = 0; d < g.length; d++) HTMLCS.addMessage(HTMLCS.WARNING, g[d], _global.HTMLCS.getTranslation("2_1_1_SCR20.MouseMove"), "SCR20.MouseMove");
                for (var h = HTMLCS.util.getAllElements(b, "*[onmousedown]"), d = 0; d < h.length; d++) HTMLCS.addMessage(HTMLCS.WARNING, h[d], _global.HTMLCS.getTranslation("2_1_1_SCR20.MouseDown"), "SCR20.MouseDown");
                for (var i = HTMLCS.util.getAllElements(b, "*[onmouseup]"), d = 0; d < i.length; d++) HTMLCS.addMessage(HTMLCS.WARNING, i[d], _global.HTMLCS.getTranslation("2_1_1_SCR20.MouseUp"), "SCR20.MouseUp")
            }
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_1_2_1_2 = {
        register: function() {
            return ["object", "applet", "embed"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("2_1_2_F10"), "F10")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_2_2_2_1 = {
        register: function() {
            return ["meta"]
        },
        process: function(a, b) {
            !0 === a.hasAttribute("http-equiv") && "refresh" === String(a.getAttribute("http-equiv")).toLowerCase() && !0 === /^[1-9]\d*/.test(a.getAttribute("content").toLowerCase()) && (!0 === /url=/.test(a.getAttribute("content").toLowerCase()) ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("2_2_1_F40.2"), "F40.2") : HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("2_2_1_F41.2"), "F41.2"))
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_2_2_2_2 = {
        register: function() {
            return ["_top", "blink"]
        },
        process: function(a, b) {
            if (a === b) {
                HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_2_2_SCR33,SCR22,G187,G152,G186,G191"), "SCR33,SCR22,G187,G152,G186,G191");
                for (var c = HTMLCS.util.getAllElements(b, "*"), d = 0; d < c.length; d++) {
                    var e = HTMLCS.util.style(c[d]);
                    e && !0 === /blink/.test(e["text-decoration"]) && HTMLCS.addMessage(HTMLCS.WARNING, c[d], _global.HTMLCS.getTranslation("2_2_2_F4"), "F4")
                }
            } else "blink" === a.nodeName.toLowerCase() && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("2_2_2_F47"), "F47")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_2_2_2_3 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_2_3_G5"), "G5")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_2_2_2_4 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_2_4_SCR14"), "SCR14")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_2_2_2_5 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_2_5_G105,G181"), "G105,G181")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_3_2_3_1 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("2_3_1_G19,G176"), "G19,G176")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_3_2_3_2 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("2_3_2_G19"), "G19")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_4_2_4_1 = {
        register: function() {
            return ["iframe", "a", "area", "_top"]
        },
        process: function(a, b) {
            if (a === b) this.testGenericBypassMsg(b);
            else {
                switch (a.nodeName.toLowerCase()) {
                    case "iframe":
                        this.testIframeTitle(a);
                        break;
                    case "a":
                    case "area":
                        this.testSameDocFragmentLinks(a, b)
                }
            }
        },
        testIframeTitle: function(a) {
            if ("iframe" === a.nodeName.toLowerCase()) {
                var b = !1;
                !0 === a.hasAttribute("title") && a.getAttribute("title") && !1 === /^\s+$/.test(a.getAttribute("title")) && (b = !0), !1 === b ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("2_4_1_H64.1"), "H64.1") : HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_4_1_H64.2"), "H64.2")
            }
        },
        testGenericBypassMsg: function(a) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_4_1_G1,G123,G124,H69"), "G1,G123,G124,H69")
        },
        testSameDocFragmentLinks: function(a, b) {
            if (!0 === a.hasAttribute("href")) {
                var c = a.getAttribute("href");
                if (c = HTMLCS.util.trim(c), c.length > 1 && "#" === c.charAt(0)) {
                    var d = c.substr(1);
                    try {
                        var e = b;
                        e.ownerDocument && (e = e.ownerDocument);
                        var f = e.getElementById(d);
                        if (null === f) {
                            var g = HTMLCS.util.getElementWindow(b).document,
                                h = HTMLCS.util.getDocumentType(g),
                                i = "a";
                            h && -1 === h.indexOf("html5") && (i = "*"), f = e.querySelector(i + '[name="' + d + '"]')
                        }
                        null !== f && !1 !== HTMLCS.util.contains(b, f) || (!0 === HTMLCS.isFullDoc(b) || "body" === b.nodeName.toLowerCase() ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("2_4_1_G1,G123,G124.NoSuchID").replace(/\{\{id\}\}/g, d), "G1,G123,G124.NoSuchID") : HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("2_4_1_G1,G123,G124.NoSuchIDFragment").replace(/\{\{id\}\}/g, d), "G1,G123,G124.NoSuchIDFragment"))
                    } catch (a) {}
                }
            }
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_4_2_4_2 = {
        register: function() {
            return ["html"]
        },
        process: function(a, b) {
            for (var c = a.childNodes, d = null, e = 0; e < c.length; e++)
                if ("head" === c[e].nodeName.toLowerCase()) {
                    d = c[e];
                    break
                } if (null === d) HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("2_4_2_H25.1.NoHeadEl"), "H25.1.NoHeadEl");
            else {
                for (var c = d.childNodes, f = null, e = 0; e < c.length; e++)
                    if ("title" === c[e].nodeName.toLowerCase()) {
                        f = c[e];
                        break
                    } null === f ? HTMLCS.addMessage(HTMLCS.ERROR, d, _global.HTMLCS.getTranslation("2_4_2_H25.1.NoTitleEl"), "H25.1.NoTitleEl") : !0 === /^\s*$/.test(f.innerHTML) ? HTMLCS.addMessage(HTMLCS.ERROR, f, _global.HTMLCS.getTranslation("2_4_2_H25.1.EmptyTitle"), "H25.1.EmptyTitle") : HTMLCS.addMessage(HTMLCS.NOTICE, f, _global.HTMLCS.getTranslation("2_4_2_H25.2"), "H25.2")
            }
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_4_2_4_3 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            if (a === b) {
                b.querySelector("*[tabindex]") && HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_4_3_H4.2"), "H4.2")
            }
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_4_2_4_4 = {
        register: function() {
            return ["a"]
        },
        process: function(a, b) {
            !0 === a.hasAttribute("title") ? HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_4_4_H77,H78,H79,H80,H81,H33"), "H77,H78,H79,H80,H81,H33") : HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_4_4_H77,H78,H79,H80,H81"), "H77,H78,H79,H80,H81")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_4_2_4_5 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_4_5_G125,G64,G63,G161,G126,G185"), "G125,G64,G63,G161,G126,G185")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_4_2_4_6 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_4_6_G130,G131"), "G130,G131")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_4_2_4_7 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            null !== b.querySelector("input, textarea, button, select, a") && HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("2_4_7_G149,G165,G195,C15,SCR31"), "G149,G165,G195,C15,SCR31")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_4_2_4_8 = {
        register: function() {
            return ["link"]
        },
        process: function(a, b) {
            "head" !== a.parentNode.nodeName.toLowerCase() && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("2_4_8_H59.1"), "H59.1"), !1 !== a.hasAttribute("rel") && a.getAttribute("rel") && !0 !== /^\s*$/.test(a.getAttribute("rel")) || HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("2_4_8_H59.2a"), "H59.2a"), !1 !== a.hasAttribute("href") && a.getAttribute("href") && !0 !== /^\s*$/.test(a.getAttribute("href")) || HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("2_4_8_H59.2b"), "H59.2b")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle2_Guideline2_4_2_4_9 = {
        register: function() {
            return ["a"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("2_4_9_H30"), "H30")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_1_3_1_1 = {
        register: function() {
            return ["html"]
        },
        process: function(a, b) {
            if (!1 === a.hasAttribute("lang") && !1 === a.hasAttribute("xml:lang")) HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("3_1_1_H57.2"), "H57.2");
            else {
                if (!0 === a.hasAttribute("lang")) {
                    var c = a.getAttribute("lang");
                    !1 === this.isValidLanguageTag(c) && HTMLCS.addMessage(HTMLCS.ERROR, b, _global.HTMLCS.getTranslation("3_1_1_H57.3.Lang"), "H57.3.Lang")
                }
                if (!0 === a.hasAttribute("xml:lang")) {
                    var c = a.getAttribute("xml:lang");
                    !1 === this.isValidLanguageTag(c) && HTMLCS.addMessage(HTMLCS.ERROR, b, _global.HTMLCS.getTranslation("3_1_1_H57.3.XmlLang"), "H57.3.XmlLang")
                }
            }
        },
        isValidLanguageTag: function(a) {
            var b = "^([ix](-[a-z0-9]{1,8})+)$|";
            b += "^[a-z]{2,8}", b += "(-[a-z]{3}){0,3}", b += "(-[a-z]{4})?", b += "(-[a-z]{2}|-[0-9]{3})?", b += "(-[0-9][a-z0-9]{3}|-[a-z0-9]{5,8})*", b += "(-[a-wy-z0-9](-[a-z0-9]{2,8})+)*", b += "(-x(-[a-z0-9]{1,8})+)?$";
            var c = new RegExp(b, "i"),
                d = !0;
            return !1 === c.test(a) && (d = !1), d
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_1_3_1_2 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("3_1_2_H58"), "H58");
            for (var c = HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_1_3_1_1, d = HTMLCS.util.getAllElements(b, "*[lang]"), e = 0; e <= d.length; e++) {
                if (e === d.length) var f = b;
                else var f = d[e];
                if (!f.documentElement && "html" !== f.nodeName.toLowerCase()) {
                    if (!0 === f.hasAttribute("lang")) {
                        var g = f.getAttribute("lang");
                        !1 === c.isValidLanguageTag(g) && HTMLCS.addMessage(HTMLCS.ERROR, f, _global.HTMLCS.getTranslation("3_1_2_H58.1.Lang"), "H58.1.Lang")
                    }
                    if (!0 === f.hasAttribute("xml:lang")) {
                        var g = f.getAttribute("xml:lang");
                        !1 === c.isValidLanguageTag(g) && HTMLCS.addMessage(HTMLCS.ERROR, f, _global.HTMLCS.getTranslation("3_1_2_H58.1.XmlLang"), "H58.1.XmlLang")
                    }
                }
            }
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_1_3_1_3 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("3_1_3_H40,H54,H60,G62,G70"), "H40,H54,H60,G62,G70")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_1_3_1_4 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("3_1_4_G102,G55,G62,H28,G97"), "G102,G55,G62,H28,G97")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_1_3_1_5 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("3_1_5_G86,G103,G79,G153,G160"), "G86,G103,G79,G153,G160")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_1_3_1_6 = {
        register: function() {
            return ["ruby"]
        },
        process: function(a, b) {
            var c = a.querySelectorAll("rb");
            0 === a.querySelectorAll("rt").length && (0 === c.length ? HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("3_1_6_H62.1.HTML5"), "H62.1.HTML5") : HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("3_1_6_H62.1.XHTML11"), "H62.1.XHTML11")), 0 === a.querySelectorAll("rp").length && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("3_1_6_H62.2"), "H62.2")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_2_3_2_1 = {
        register: function() {
            return ["input", "textarea", "button", "select"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("3_2_1_G107"), "G107")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_2_3_2_2 = {
        register: function() {
            return ["form"]
        },
        process: function(a, b) {
            "form" === a.nodeName.toLowerCase() && this.checkFormSubmitButton(a)
        },
        checkFormSubmitButton: function(a) {
            var b = !1;
            if (a.querySelectorAll("input[type=submit], input[type=image]").length > 0) b = !0;
            else {
                var c = a.querySelectorAll("button"),
                    d = a.querySelectorAll("button[type=reset], button[type=button]");
                c.length > d.length && (b = !0)
            }!1 === b && HTMLCS.addMessage(HTMLCS.ERROR, a, _global.HTMLCS.getTranslation("3_2_2_H32.2"), "H32.2")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_2_3_2_3 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("3_2_3_G61"), "G61")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_2_3_2_4 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, b, _global.HTMLCS.getTranslation("3_2_4_G197"), "G197")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_2_3_2_5 = {
        register: function() {
            return ["a"]
        },
        process: function(a, b) {
            "a" === a.nodeName.toLowerCase() && this.checkNewWindowTarget(a)
        },
        checkNewWindowTarget: function(a) {
            !0 === a.hasAttribute("target") && ("_blank" === (a.getAttribute("target") || "") && !1 === /new window/i.test(a.innerHTML) && HTMLCS.addMessage(HTMLCS.WARNING, a, _global.HTMLCS.getTranslation("3_2_5_H83.3"), "H83.3"))
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_3_3_3_1 = {
        register: function() {
            return ["form"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("3_3_1_G83,G84,G85"), "G83,G84,G85")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_3_3_3_2 = {
        register: function() {
            return ["form"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("3_3_2_G131,G89,G184,H90"), "G131,G89,G184,H90")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_3_3_3_3 = {
        register: function() {
            return ["form"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("3_3_3_G177"), "G177")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_3_3_3_4 = {
        register: function() {
            return ["form"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("3_3_4_G98,G99,G155,G164,G168.LegalForms"), "G98,G99,G155,G164,G168.LegalForms")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_3_3_3_5 = {
        register: function() {
            return ["form"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("3_3_5_G71,G184,G193"), "G71,G184,G193")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle3_Guideline3_3_3_3_6 = {
        register: function() {
            return ["form"]
        },
        process: function(a, b) {
            HTMLCS.addMessage(HTMLCS.NOTICE, a, _global.HTMLCS.getTranslation("3_3_6_G98,G99,G155,G164,G168.AllForms"), "G98,G99,G155,G164,G168.AllForms")
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle4_Guideline4_1_4_1_1 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            if (a === b)
                for (var c = HTMLCS.util.getAllElements(b, "*[id]"), d = {}, e = 0; e < c.length; e++) {
                    var f = c[e].getAttribute("id");
                    !0 !== /^\s*$/.test(f) && (void 0 !== d[f] ? HTMLCS.addMessage(HTMLCS.ERROR, c[e], _global.HTMLCS.getTranslation("4_1_1_F77").replace(/\{\{id\}\}/g, f), "F77") : d[f] = !0)
                }
        }
    }, _global.HTMLCS_WCAG2AAA_Sniffs_Principle4_Guideline4_1_4_1_2 = {
        register: function() {
            return ["_top"]
        },
        process: function(a, b) {
            if (a === b) {
                for (var c = this.processFormControls(b), d = 0; d < c.errors.length; d++) HTMLCS.addMessage(HTMLCS.ERROR, c.errors[d].element, c.errors[d].msg, "H91." + c.errors[d].subcode);
                for (var d = 0; d < c.warnings.length; d++) HTMLCS.addMessage(HTMLCS.WARNING, c.warnings[d].element, c.warnings[d].msg, "H91." + c.warnings[d].subcode);
                this.addProcessLinksMessages(b)
            }
        },
        addProcessLinksMessages: function(a) {
            for (var b = this.processLinks(a), c = 0; c < b.empty.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, b.empty[c], _global.HTMLCS.getTranslation("4_1_2_H91.A.Empty"), "H91.A.Empty");
            for (var c = 0; c < b.emptyWithName.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, b.emptyWithName[c], _global.HTMLCS.getTranslation("4_1_2_H91.A.EmptyWithName"), "H91.A.EmptyWithName");
            for (var c = 0; c < b.emptyNoId.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.emptyNoId[c], _global.HTMLCS.getTranslation("4_1_2_H91.A.EmptyNoId"), "H91.A.EmptyNoId");
            for (var c = 0; c < b.noHref.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, b.noHref[c], _global.HTMLCS.getTranslation("4_1_2_H91.A.NoHref"), "H91.A.NoHref");
            for (var c = 0; c < b.placeholder.length; c++) HTMLCS.addMessage(HTMLCS.WARNING, b.placeholder[c], _global.HTMLCS.getTranslation("4_1_2_H91.A.Placeholder"), "H91.A.Placeholder");
            for (var c = 0; c < b.noContent.length; c++) HTMLCS.addMessage(HTMLCS.ERROR, b.noContent[c], _global.HTMLCS.getTranslation("4_1_2_H91.A.NoContent"), "H91.A.NoContent")
        },
        processLinks: function(a) {
            for (var b = {
                    empty: [],
                    emptyWithName: [],
                    emptyNoId: [],
                    noHref: [],
                    placeholder: [],
                    noContent: []
                }, c = HTMLCS.util.getAllElements(a, 'a:not([role="button"])'), d = 0; d < c.length; d++) {
                var e = c[d],
                    f = !1,
                    g = !1,
                    h = HTMLCS.util.getElementTextContent(e);
                !0 === e.hasAttribute("title") && !1 === /^\s*$/.test(e.getAttribute("title")) ? f = !0 : !1 === /^\s*$/.test(h) && (f = !0), !0 === e.hasAttribute("href") && !1 === /^\s*$/.test(e.getAttribute("href")) && (g = !0), !1 === g ? !0 === /^\s*$/.test(h) ? !0 === e.hasAttribute("id") ? b.empty.push(e) : !0 === e.hasAttribute("name") ? b.emptyWithName.push(e) : b.emptyNoId.push(e) : !0 === e.hasAttribute("id") || !0 === e.hasAttribute("name") ? b.noHref.push(e) : b.placeholder.push(e) : !1 === f && 0 === e.querySelectorAll("img").length && !1 === HTMLCS.util.hasValidAriaLabel(e) && b.noContent.push(e)
            }
            return b
        },
        processFormControls: function(a) {
            for (var b = HTMLCS.util.getAllElements(a, 'button, fieldset, input, select, textarea, [role="button"]'), c = [], d = [], e = {
                    button: ["@title", "_content", "@aria-label", "@aria-labelledby"],
                    fieldset: ["legend", "@aria-label", "@aria-labelledby"],
                    input_button: ["@value", "@aria-label", "@aria-labelledby"],
                    input_text: ["label", "@title", "@aria-label", "@aria-labelledby"],
                    input_file: ["label", "@title", "@aria-label", "@aria-labelledby"],
                    input_password: ["label", "@title", "@aria-label", "@aria-labelledby"],
                    input_checkbox: ["label", "@title", "@aria-label", "@aria-labelledby"],
                    input_radio: ["label", "@title", "@aria-label", "@aria-labelledby"],
                    input_image: ["@alt", "@title", "@aria-label", "@aria-labelledby"],
                    select: ["label", "@title", "@aria-label", "@aria-labelledby"],
                    textarea: ["label", "@title", "@aria-label", "@aria-labelledby"]
                }, f = ["email", "search", "date", "datetime-local", "month", "number", "tel", "time", "url", "week", "range", "color"], g = 0, h = f.length; g < h; g++) e["input_" + f[g]] = ["label", "@title", "@aria-label", "@aria-labelledby"];
            for (var i = {
                    select: "option_selected"
                }, j = 0, k = b.length; j < k; j++) {
                var l = b[j],
                    m = l.nodeName.toLowerCase(),
                    n = l.nodeName.substr(0, 1).toUpperCase() + l.nodeName.substr(1).toLowerCase();
                if ("input" === m) {
                    !1 === l.hasAttribute("type") ? m += "_text" : m += "_" + l.getAttribute("type").toLowerCase(), "input_submit" !== m && "input_reset" !== m || (m = "input_button");
                    var n = "Input" + m.substr(6, 1).toUpperCase() + m.substr(7).toLowerCase()
                }
                var o = e[m],
                    p = i[m];
                if (o || "input_hidden" === m || (o = ["_content", "@aria-label", "@aria-labelledby"]), o) {
                    for (var g = 0; g < o.length; g++) {
                        var q = o[g];
                        if ("_content" === q) {
                            var r = HTMLCS.util.getElementTextContent(l);
                            if (!1 === /^\s*$/.test(r)) break
                        } else if ("label" === q) {
                            var s = HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_1.testLabelsOnInputs(l, a, !0);
                            if (!1 !== s) break
                        } else if ("@" === q.charAt(0)) {
                            if (("aria-label" === (q = q.substr(1, q.length)) || "aria-labelledby" === q) && HTMLCS.util.hasValidAriaLabel(l)) break;
                            if (!0 === l.hasAttribute(q) && !1 === /^\s*$/.test(l.getAttribute(q))) break
                        } else {
                            var t = l.querySelector(q);
                            if (null !== t) {
                                var r = HTMLCS.util.getElementTextContent(t);
                                if (!1 === /^\s*$/.test(r)) break
                            }
                        }
                    }
                    if (g === o.length) {
                        var u = m + " " + _global.HTMLCS.getTranslation("4_1_2_element");
                        "input_" === m.substr(0, 6) && (u = m.substr(6) + _global.HTMLCS.getTranslation("4_1_2_input_element")), l.hasAttribute("role") && "button" === l.getAttribute("role") && (u = _global.HTMLCS.getTranslation("4_1_2_role_of_button"));
                        for (var v = o.slice(0, o.length), w = 0; w < v.length; w++) "_content" === v[w] ? v[w] = _global.HTMLCS.getTranslation("4_1_2_element_content") : "@" === v[w].charAt(0) ? v[w] = v[w].substr(1) + " " + _global.HTMLCS.getTranslation("4_1_2_attribute") : v[w] = v[w] + " " + _global.HTMLCS.getTranslation("4_1_2_element");
                        var x = _global.HTMLCS.getTranslation("4_1_2_msg_pattern").replace(/\{\{msgNodeType\}\}/g, u).replace(/\{\{builtAttrs\}\}/g, v.join(", "));
                        c.push({
                            element: l,
                            msg: x,
                            subcode: n + ".Name"
                        })
                    }
                }
                var y = !1;
                if (void 0 === p) y = !0;
                else if ("_content" === p) {
                    var r = HTMLCS.util.getElementTextContent(l);
                    !1 === /^\s*$/.test(r) && (y = !0)
                } else if ("option_selected" === p)
                    if (!1 === l.hasAttribute("multiple")) {
                        var z = l.querySelector("option[selected]");
                        null !== z && (y = !0)
                    } else y = !0;
                else "@" === p.charAt(0) && (p = p.substr(1, p.length), !0 === l.hasAttribute(p) && (y = !0));
                if (!1 === y && (valuFound = HTMLCS.util.hasValidAriaLabel(l)), !1 === y) {
                    var u = m + " " + _global.HTMLCS.getTranslation("4_1_2_element");
                    "input_" === m.substr(0, 6) && (u = m.substr(6) + " input element");
                    var x = _global.HTMLCS.getTranslation("4_1_2_msg_pattern2").replace(/\{\{msgNodeType\}\}/g, u),
                        A = "",
                        B = !1;
                    "_content" === p ? A = " " + _global.HTMLCS.getTranslation("4_1_2_msg_add_one") : "option_selected" === p ? (B = !0, x = _global.HTMLCS.getTranslation("4_1_2_msg_pattern2").replace(/\{\{msgNodeType\}\}/g, u)) : A = "@" === p.charAt(0) ? " " + _global.HTMLCS.getTranslation("4_1_2_value_exposed_using_attribute").replace(/\{\{requiredValue\}\}/g, p) : " " + _global.HTMLCS.getTranslation("4_1_2_value_exposed_using_element").replace(/\{\{requiredValue\}\}/g, p), x += A, !1 === B ? c.push({
                        element: l,
                        msg: x,
                        subcode: n + ".Value"
                    }) : d.push({
                        element: l,
                        msg: x,
                        subcode: n + ".Value"
                    })
                }
            }
            return {
                errors: c,
                warnings: d
            }
        }
    }, _global.HTMLCS = new function() {
        var a = {},
            b = [],
            c = {},
            d = null,
            e = null,
            f = [],
            g = {};
        this.ERROR = 1, this.WARNING = 2, this.NOTICE = 3, this.lang = "es", this.process = function(e, f, g, h, i) {
            if (a = {}, b = [], c = {}, d = null, !f) return !1;
            var j = Object.keys(_global.translation);
            i && -1 !== j.indexOf(i) && (this.lang = i), a[p(e)] ? HTMLCS.run(g, f) : this.loadStandard(e, function() {
                HTMLCS.run(g, f)
            }, h)
        }, this.getTranslation = function(a) {
            try {
                return _global.translation[this.lang][a]
            } catch (b) {
                throw new Error('Translation for "' + a + '" does not exist in current language ' + this.lang)
            }
        }, this.loadStandard = function(a, b, c) {
            if (!a) return !1;
            j(a, function() {
                d = a, b.call(this)
            }, c)
        }, this.run = function(a, b) {
            var c = null,
                d = !1;
            if ("string" == typeof b) {
                d = !0;
                var e = document.createElement("iframe");
                e.style.display = "none", e = document.body.insertBefore(e, null), e.contentDocument ? c = e.contentDocument : c.contentWindow && (c = e.contentWindow.document), e.load = function() {
                    if (this.onreadystatechange = null, this.onload = null, !1 === HTMLCS.isFullDoc(b)) {
                        c = c.getElementsByTagName("body")[0];
                        var d = c.getElementsByTagName("div")[0];
                        d && "__HTMLCS-source-wrap" === d.id && (d.id = "", c = d)
                    }
                    var e = HTMLCS.util.getAllElements(c);
                    e.unshift(c), h(e, c, a)
                }, e.onreadystatechange = function() {
                    !0 === /^(complete|loaded)$/.test(this.readyState) && (this.onreadystatechange = null, this.load())
                }, e.onload = e.load, !1 === HTMLCS.isFullDoc(b) && -1 === b.indexOf("<body") ? c.write('<div id="__HTMLCS-source-wrap">' + b + "</div>") : c.write(b), c.close()
            } else c = b;
            if (!c) return void a.call(this);
            a = a || function() {}, f = [];
            var g = HTMLCS.util.getAllElements(c);
            g.unshift(c), !1 === d && h(g, c, a)
        }, this.isFullDoc = function(a) {
            var b = !1;
            return "string" == typeof a ? -1 !== a.toLowerCase().indexOf("<html") ? b = !0 : -1 !== a.toLowerCase().indexOf("<head") && -1 !== a.toLowerCase().indexOf("<body") && (b = !0) : ("html" === a.nodeName.toLowerCase() || a.documentElement) && (b = !0), b
        }, this.addMessage = function(a, b, c, d, e) {
            d = r(d), f.push({
                type: a,
                element: b,
                msg: g[d] || c,
                code: d,
                data: e
            })
        }, this.getMessages = function() {
            return f.concat([])
        };
        var h = function(a, b, d) {
                for (var g = []; a.length > 0;) {
                    var h = a.shift();
                    if (h === b) var j = "_top";
                    else var j = h.tagName.toLowerCase();
                    for (var k = 0; k < g.length;) h === g[k].element ? (f.push(g[k]), g.splice(k, 1)) : k++;
                    c[j] && c[j].length > 0 && (i(h, c[j].concat([]), b), "_top" === j && (g = f, f = []))
                }
                f = f.concat(g);
                var l = b.querySelectorAll('[role="presentation"]');
                e = HTMLCS_WCAG2AAA_Sniffs_Principle1_Guideline1_3_1_3_1, [].forEach.call(l, function(a) {
                    e.testSemanticPresentationRole(a)
                }), d instanceof Function == !0 && d.call(this)
            },
            i = function(a, b, c, d) {
                for (; b.length > 0;) {
                    var f = b.shift();
                    e = f, !0 === f.useCallback ? f.process(a, c, function() {
                        i(a, b, c), b = []
                    }) : f.process(a, c)
                }
                d instanceof Function == !0 && d.call(this)
            },
            j = function(a, b, c, d) {
                0 !== a.indexOf("http") && (a = p(a));
                var e = a.split("/");
                _global["HTMLCS_" + e[e.length - 2]] ? k(a, b, c, d) : s(a, function() {
                    k(a, b, c, d)
                }, c)
            },
            k = function(b, c, d, e) {
                var f = b.split("/"),
                    g = _global["HTMLCS_" + f[f.length - 2]],
                    h = {};
                for (var i in g) !0 === g.hasOwnProperty(i) && (h[i] = g[i]);
                if (!h) return !1;
                if (a[b] = h, e)
                    if (e.include && e.include.length > 0) h.sniffs = e.include;
                    else if (e.exclude)
                    for (var j = 0; j < e.exclude.length; j++) {
                        var k = h.sniffs.find(e.exclude[j]);
                        k >= 0 && h.sniffs.splice(k, 1)
                    }
                var m = h.sniffs.slice(0, h.sniffs.length);
                l(b, m, c, d)
            },
            l = function(a, b, c, d) {
                if (0 === b.length) return void c.call(this);
                var e = b.shift();
                m(a, e, function() {
                    l(a, b, c, d)
                }, d)
            },
            m = function(a, b, c, d) {
                if ("string" == typeof b) {
                    var e = q(a, b),
                        f = function() {
                            n(a, b), c.call(this)
                        };
                    e ? f() : s(o(a, b), f, d)
                } else j(b.standard, function() {
                    if (b.messages)
                        for (var a in b.messages) g[a] = b.messages[a];
                    c.call(this)
                }, d, {
                    exclude: b.exclude,
                    include: b.include
                })
            },
            n = function(a, d) {
                var e = q(a, d);
                if (!e) return !1;
                if (e.register)
                    for (var f = e.register(), g = 0; g < f.length; g++) c[f[g]] || (c[f[g]] = []), c[f[g]].push(e);
                b.push(e)
            },
            o = function(a, b) {
                var c = a.split("/");
                return c.pop(), c.join("/") + "/Sniffs/" + b.replace(/\./g, "/") + ".js"
            },
            p = function(a) {
                for (var b = document.getElementsByTagName("script"), c = null, d = 0; d < b.length; d++)
                    if (b[d].src && b[d].src.match(/HTMLCS\.js/)) {
                        c = b[d].src.replace(/HTMLCS\.js/, ""), c = c.substring(0, c.indexOf("?"));
                        break
                    } return c + "Standards/" + a + "/ruleset.js"
            },
            q = function(b, c) {
                var d = "HTMLCS_";
                return d += a[b].name + "_Sniffs_", d += c.split(".").join("_"), _global[d] ? (_global[d]._name = c, _global[d]) : null
            },
            r = function(a) {
                return a = d + "." + e._name + "." + a
            },
            s = function(a, b, c) {
                var d = document.createElement("script");
                d.onload = function() {
                    d.onload = null, d.onreadystatechange = null, b.call(this)
                }, d.onerror = function() {
                    d.onload = null, d.onreadystatechange = null, c && c.call(this)
                }, d.onreadystatechange = function() {
                    !0 === /^(complete|loaded)$/.test(this.readyState) && (d.onreadystatechange = null, d.onload())
                }, d.src = a, document.head ? document.head.appendChild(d) : document.getElementsByTagName("head")[0].appendChild(d)
            }
    }, _global.HTMLCS.util = function() {
        var a = {};
        return a.trim = function(a) {
            return a.replace(/^\s*(.*)\s*$/g, "$1")
        }, a.isStringEmpty = function(a) {
            if ("string" != typeof a) return !0;
            var b = !0;
            return -1 !== a.indexOf(String.fromCharCode(160)) ? b = !1 : !1 === /^\s*$/.test(a) && (b = !1), b
        }, a.getDocumentType = function(a) {
            var b = null,
                c = a.doctype;
            if (c) {
                var d = c.name,
                    e = c.publicId,
                    f = c.systemId;
                if (null === d && (d = ""), null === f && (f = ""), null === e && (e = ""), "html" === d.toLowerCase() && ("" === e && "" === f ? b = "html5" : -1 !== e.indexOf("//DTD HTML 4.01") || -1 !== f.indexOf("w3.org/TR/html4/strict.dtd") ? b = "html401" : -1 !== e.indexOf("//DTD HTML 4.0") || -1 !== f.indexOf("w3.org/TR/REC-html40/strict.dtd") ? b = "html40" : -1 !== e.indexOf("//DTD XHTML 1.0 Strict") && -1 !== f.indexOf("w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd") ? b = "xhtml10" : -1 !== e.indexOf("//DTD XHTML 1.1") && -1 !== f.indexOf("w3.org/TR/xhtml11/DTD/xhtml11.dtd") && (b = "xhtml11"), -1 !== f.indexOf("about:legacy-compat") && "application/xhtml+xml" === a.contentType)) {
                    var g = a.querySelector("html");
                    "http://www.w3.org/1999/xhtml" === g.getAttribute("xmlns") && (b = "xhtml5")
                }
            } else if ("application/xhtml+xml" === a.contentType) {
                var g = a.querySelector("html");
                "http://www.w3.org/1999/xhtml" === g.getAttribute("xmlns") && (b = "xhtml5")
            }
            return b
        }, a.getElementWindow = function(a) {
            if (a.ownerDocument) var b = a.ownerDocument;
            else var b = a;
            return b.defaultView ? b.defaultView : b.parentWindow
        }, a.hasValidAriaLabel = function(b) {
            var c = !1;
            if (!0 === b.hasAttribute("aria-labelledby")) {
                b.getAttribute("aria-labelledby").split(/\s+/).forEach(function(b) {
                    var d = document.getElementById(b);
                    if (d) {
                        !1 === /^\s*$/.test(a.getElementTextContent(d)) && (c = !0)
                    }
                })
            } else if (!0 === b.hasAttribute("aria-label")) {
                var d = b.getAttribute("aria-label");
                !1 === /^\s*$/.test(d) && (c = !0)
            }
            return c
        }, a.style = function(b, c) {
            var d = null,
                e = a.getElementWindow(b),
                c = c || null;
            return b.currentStyle ? d = b.currentStyle : e.getComputedStyle && (d = e.getComputedStyle(b, c)), d
        }, a.isVisuallyHidden = function(b) {
            var c = !1,
                d = a.style(b);
            return null !== d && ("hidden" !== d.visibility && "none" !== d.display || (c = !0), parseInt(d.left, 10) + parseInt(d.width, 10) < 0 && (c = !0), parseInt(d.top, 10) + parseInt(d.height, 10) < 0 && (c = !0)), c
        }, a.isAriaHidden = function(a) {
            do {
                if (a.hasAttribute("aria-hidden") && "true" === a.getAttribute("aria-hidden")) return !0
            } while (a = a.parentElement);
            return !1
        }, a.isAccessibilityHidden = function(a) {
            do {
                if (a.hasAttribute("role") && "presentation" === a.getAttribute("role")) return !0;
                if (a.hasAttribute("aria-hidden") && "true" === a.getAttribute("aria-hidden")) return !0
            } while (a = a.parentElement);
            return !1
        }, a.isFocusable = function(b) {
            var c = b.nodeName.toLowerCase();
            return !0 !== a.isDisabled(b) && (!0 !== a.isVisuallyHidden(b) && (!!/^(input|select|textarea|button|object)$/.test(c) || !("a" !== c || !b.hasAttribute("href") || !1 !== /^\s*$/.test(b.getAttribute("href")))))
        }, a.isKeyboardTabbable = function(b) {
            if (!0 === b.hasAttribute("tabindex")) {
                return "-1" !== b.getAttribute("tabindex")
            }
            return a.isFocusable(b)
        }, a.isKeyboardNavigable = function(b) {
            return !(!b.hasAttribute("accesskey") || !1 !== /^\s*$/.test(b.getAttribute("accesskey"))) || a.isKeyboardTabbable(b)
        }, a.isDisabled = function(a) {
            var b = !1;
            return !0 !== a.disabled && "true" !== a.getAttribute("aria-disabled") || (b = !0), b
        }, a.isInDocument = function(a) {
            for (var b = a.parentNode; b && b.ownerDocument;) b = b.parentNode;
            return null !== b
        }, a.getAllElements = function(a, b) {
            a = a || document, b = b || "*";
            var c = Array.prototype.slice.call(a.querySelectorAll(b)),
                d = c.filter(function(a) {
                    return !1 === HTMLCS.util.isAccessibilityHidden(a)
                }),
                e = document.getElementById("HTMLCS-wrapper");
            return e && (d = d.filter(function(a) {
                return !1 === e.contains(a)
            })), d
        }, a.contains = function(a, b) {
            var c = !1;
            return a !== b && (a.ownerDocument ? a.contains && !0 === a.contains(b) ? c = !0 : a.compareDocumentPosition && (16 & a.compareDocumentPosition(b)) > 0 && (c = !0) : b.ownerDocument && b.ownerDocument === a && (c = !0)), c
        }, a.isLayoutTable = function(a) {
            return null === a.querySelector("th")
        }, a.contrastRatio = function(b, c) {
            var d = (.05 + a.relativeLum(b)) / (.05 + a.relativeLum(c));
            return d < 1 && (d = 1 / d), d
        }, a.relativeLum = function(b) {
            if (b.charAt) var b = a.colourStrToRGB(b);
            var c = {};
            for (var d in b) b[d] <= .03928 ? c[d] = b[d] / 12.92 : c[d] = Math.pow((b[d] + .055) / 1.055, 2.4);
            return .2126 * c.red + .7152 * c.green + .0722 * c.blue
        }, a.colourStrToRGB = function(a) {
            if (a = a.toLowerCase(), "rgb" === a.substring(0, 3)) {
                var b = /^rgba?\s*\((\d+),\s*(\d+),\s*(\d+)([^)]*)\)$/.exec(a);
                a = {
                    red: b[1] / 255,
                    green: b[2] / 255,
                    blue: b[3] / 255,
                    alpha: 1
                }, b[4] && (a.alpha = parseFloat(/^,\s*(.*)$/.exec(b[4])[1]))
            } else {
                "#" === a.charAt(0) && (a = a.substr(1)), 3 === a.length && (a = a.replace(/^(.)(.)(.)$/, "$1$1$2$2$3$3")), 4 === a.length && (a = a.replace(/^(.)(.)(.)(.)$/, "$1$1$2$2$3$3$4$4"));
                var c = 1;
                8 === a.length && (c = parseInt(a.substr(6, 2), 16) / 255), a = {
                    red: parseInt(a.substr(0, 2), 16) / 255,
                    green: parseInt(a.substr(2, 2), 16) / 255,
                    blue: parseInt(a.substr(4, 2), 16) / 255,
                    alpha: c
                }
            }
            return a
        }, a.RGBtoColourStr = function(a) {
            return colourStr = "#", a.red = Math.round(255 * a.red), a.green = Math.round(255 * a.green), a.blue = Math.round(255 * a.blue), a.red % 17 == 0 && a.green % 17 == 0 && a.blue % 17 == 0 ? (colourStr += (a.red / 17).toString(16), colourStr += (a.green / 17).toString(16), colourStr += (a.blue / 17).toString(16)) : (a.red < 16 && (colourStr += "0"), colourStr += a.red.toString(16), a.green < 16 && (colourStr += "0"), colourStr += a.green.toString(16), a.blue < 16 && (colourStr += "0"), colourStr += a.blue.toString(16)), colourStr
        }, a.sRGBtoHSV = function(b) {
            b.charAt && (b = a.colourStrToRGB(b));
            var c = {
                    hue: 0,
                    saturation: 0,
                    value: 0
                },
                d = Math.max(b.red, b.green, b.blue),
                e = Math.min(b.red, b.green, b.blue),
                f = d - e;
            return 0 === f ? c.value = b.red : (c.value = d, d === b.red ? c.hue = (b.green - b.blue) / f : d === b.green ? c.hue = 2 + (b.blue - b.red) / f : c.hue = 4 + (b.red - b.green) / f, c.hue = 60 * c.hue, c.hue >= 360 && (c.hue -= 360), c.saturation = f / c.value), c
        }, a.HSVtosRGB = function(a) {
            var b = {
                red: 0,
                green: 0,
                blue: 0
            };
            if (0 === a.saturation) b.red = a.value, b.green = a.value, b.blue = a.value;
            else {
                var c = a.value * a.saturation,
                    d = a.value - c,
                    e = a.hue / 60,
                    f = e - 2 * Math.floor(e / 2),
                    g = c * (1 - Math.abs(f - 1));
                switch (Math.floor(e)) {
                    case 0:
                        b.red = c, b.green = g;
                        break;
                    case 1:
                        b.green = c, b.red = g;
                        break;
                    case 2:
                        b.green = c, b.blue = g;
                        break;
                    case 3:
                        b.blue = c, b.green = g;
                        break;
                    case 4:
                        b.blue = c, b.red = g;
                        break;
                    case 5:
                        b.red = c, b.blue = g
                }
                b.red = b.red + d, b.green = b.green + d, b.blue = b.blue + d
            }
            return b
        }, a.getElementTextContent = function(a, b) {
            void 0 === b && (b = !0);
            for (var a = a.cloneNode(!0), c = [], d = 0; d < a.childNodes.length; d++) c.push(a.childNodes[d]);
            for (var e = [a.textContent]; c.length > 0;) {
                var f = c.shift();
                if (1 === f.nodeType)
                    if ("img" === f.nodeName.toLowerCase()) !0 === b && !0 === f.hasAttribute("alt") && e.push(f.getAttribute("alt"));
                    else
                        for (var d = 0; d < f.childNodes.length; d++) c.push(f.childNodes[d]);
                else 3 === f.nodeType && e.push(f.nodeValue)
            }
            return e = e.join("").replace(/^\s+|\s+$/g, "")
        }, a.findParentNode = function(a, b) {
            if (a && a.matches && a.matches(b)) return a;
            for (; a && a.parentNode;)
                if ((a = a.parentNode) && a.matches && a.matches(b)) return a;
            return null
        }, a.eachParentNode = function(a, b) {
            for (; a && a.parentNode;) b(a), a = a.parentNode
        }, a.isPhrasingNode = function(a) {
            return -1 !== ["abbr", "audio", "b", "bdo", "br", "button", "canvas", "cite", "code", "command", "data", "datalist", "dfn", "em", "embed", "i", "iframe", "img", "input", "kbd", "keygen", "label", "mark", "math", "meter", "noscript", "object", "output", "progress", "q", "ruby", "samp", "script", "select", "small", "span", "strong", "sub", "sup", "svg", "textarea", "time", "var", "video", "wbr"].indexOf(a.toLowerCase())
        }, a.getChildrenForTable = function(b, c) {
            if ("table" !== b.nodeName.toLowerCase()) return null;
            for (var d = [], e = b.getElementsByTagName(c), f = 0, g = e.length; f < g; f++) a.findParentNode(e[f], "table") === b && d.push(e[f]);
            return d
        }, a.testTableHeaders = function(b) {
            for (var c = {
                    required: !0,
                    used: !1,
                    correct: !0,
                    allowScope: !0,
                    missingThId: [],
                    missingTd: [],
                    wrongHeaders: []
                }, d = a.getChildrenForTable(b, "tr"), e = [], f = {
                    rows: [],
                    cols: []
                }, g = {
                    rows: 0,
                    cols: 0
                }, h = 0; h < d.length; h++)
                for (var i = d[h], j = 0, k = 0; k < i.childNodes.length; k++) {
                    var l = i.childNodes[k];
                    if (1 === l.nodeType) {
                        if (e[h])
                            for (; e[h][0] === j;) e[h].shift(), j++;
                        var m = l.nodeName.toLowerCase(),
                            n = Number(l.getAttribute("rowspan")) || 1,
                            o = Number(l.getAttribute("colspan")) || 1;
                        if (n > 1)
                            for (var p = h + 1; p < h + n; p++) {
                                e[p] || (e[p] = []);
                                for (var q = j; q < j + o; q++) e[p].push(q)
                            }
                        if ("th" === m) {
                            var r = l.getAttribute("id") || "";
                            "" === r && (c.correct = !1, c.missingThId.push(l)), n > 1 && o > 1 ? c.allowScope = !1 : !0 === c.allowScope && (void 0 === f.cols[j] && (f.cols[j] = 0), void 0 === f.rows[h] && (f.rows[h] = 0), f.rows[h] += o, f.cols[j] += n)
                        } else "td" === m && !0 === l.hasAttribute("headers") && !1 === /^\s*$/.test(l.getAttribute("headers")) && (c.used = !0);
                        j += o
                    }
                }
            for (var p = 0; p < f.rows.length; p++) f.rows[p] > 1 && g.rows++;
            for (var p = 0; p < f.cols.length; p++) f.cols[p] > 1 && g.cols++;
            g.rows > 1 || g.cols > 1 ? c.allowScope = !1 : !0 !== c.allowScope || 0 !== g.rows && 0 !== g.cols || (c.required = !1);
            for (var s = HTMLCS.util.getCellHeaders(b), p = 0; p < s.length; p++) {
                var l = s[p].cell,
                    t = s[p].headers;
                if (!1 === l.hasAttribute("headers")) c.correct = !1, c.missingTd.push(l);
                else {
                    var u = (l.getAttribute("headers") || "").split(/\s+/);
                    if (0 === u.length) c.correct = !1, c.missingTd.push(l);
                    else if (u = " " + u.sort().join(" ") + " ", u = u.replace(/\s+/g, " ").replace(/(\w+\s)\1+/g, "$1").replace(/^\s*(.*?)\s*$/g, "$1"), t !== u) {
                        c.correct = !1;
                        var v = {
                            element: l,
                            expected: t,
                            actual: l.getAttribute("headers") || ""
                        };
                        c.wrongHeaders.push(v)
                    }
                }
            }
            return c
        }, a.getCellHeaders = function(b) {
            if ("object" != typeof b) return null;
            if ("table" !== b.nodeName.toLowerCase()) return null;
            for (var c = a.getChildrenForTable(b, "tr"), d = [], e = {
                    rows: {},
                    cols: {}
                }, f = [], g = ["th", "td"], h = 0; h < g.length; h++)
                for (var i = g[h], j = 0; j < c.length; j++)
                    for (var k = c[j], l = 0, m = 0; m < k.childNodes.length; m++) {
                        var n = k.childNodes[m];
                        if (1 === n.nodeType) {
                            if (d[j])
                                for (; d[j][l];) l++;
                            var o = n.nodeName.toLowerCase(),
                                p = Number(n.getAttribute("rowspan")) || 1,
                                q = Number(n.getAttribute("colspan")) || 1;
                            if (p > 1)
                                for (var r = j + 1; r < j + p; r++) {
                                    d[r] || (d[r] = []);
                                    for (var s = l; s < l + q; s++) d[r][s] = !0
                                }
                            if (o === i)
                                if ("th" === o) {
                                    for (var t = n.getAttribute("id") || "", r = j; r < j + p; r++) e.rows[r] = e.rows[r] || {
                                        first: l,
                                        ids: []
                                    }, e.rows[r].ids.push(t);
                                    for (var r = l; r < l + q; r++) e.cols[r] = e.cols[r] || {
                                        first: j,
                                        ids: []
                                    }, e.cols[r].ids.push(t)
                                } else if ("td" === o) {
                                for (var u = [], r = j; r < j + p; r++)
                                    for (var s = l; s < l + q; s++) e.rows[r] && s >= e.rows[r].first && (u = u.concat(e.rows[r].ids)), e.cols[s] && r >= e.cols[s].first && (u = u.concat(e.cols[s].ids));
                                if (u.length > 0) {
                                    var v = u.sort().filter(function(a, b, c) {
                                        return c.indexOf(a) === b
                                    });
                                    u = " " + v.join(" ") + " ", u = u.replace(/\s+/g, " ").replace(/(\w+\s)\1+/g, "$1").replace(/^\s*(.*?)\s*$/g, "$1"), f.push({
                                        cell: n,
                                        headers: u
                                    })
                                }
                            }
                            l += q
                        }
                    }
            return f
        }, a.getPreviousSiblingElement = function(a, b, c) {
            void 0 === b && (b = null), void 0 === c && (c = !1);
            for (var d = a.previousSibling; null !== d;) {
                if (3 === d.nodeType) {
                    if (!1 === HTMLCS.util.isStringEmpty(d.nodeValue) && !0 === c) {
                        d = null;
                        break
                    }
                } else if (1 === d.nodeType) {
                    if (null === b || d.nodeName.toLowerCase() === b) break;
                    if (!0 === c) {
                        d = null;
                        break
                    }
                    break
                }
                d = d.previousSibling
            }
            return d
        }, a.getNextSiblingElement = function(a, b, c) {
            void 0 === b && (b = null), void 0 === c && (c = !1);
            for (var d = a.nextSibling; null !== d;) {
                if (3 === d.nodeType) {
                    if (!1 === HTMLCS.util.isStringEmpty(d.nodeValue) && !0 === c) {
                        d = null;
                        break
                    }
                } else if (1 === d.nodeType) {
                    if (null === b || d.nodeName.toLowerCase() === b) break;
                    if (!0 === c) {
                        d = null;
                        break
                    }
                    break
                }
                d = d.nextSibling
            }
            return d
        }, a
    }();
    var HTMLCS_RUNNER = _global.HTMLCS_RUNNER = new function() {
        this.run = function(a, b) {
            var c = this;
            HTMLCS.process(a, document, function() {
                var a = HTMLCS.getMessages(),
                    b = a.length,
                    d = {};
                d[HTMLCS.ERROR] = 0, d[HTMLCS.WARNING] = 0, d[HTMLCS.NOTICE] = 0;
                for (var e = 0; e < b; e++) c.output(a[e]), d[a[e].type]++;
                console.log("done")
            }, function() {
                console.log("Something in HTML_CodeSniffer failed to parse. Cannot run."), console.log("done")
            }, "en")
        }, this.output = function(a) {
            var b = "UNKNOWN";
            switch (a.type) {
                case HTMLCS.ERROR:
                    b = _global.HTMLCS.getTranslation("auditor_error");
                    break;
                case HTMLCS.WARNING:
                    b = _global.HTMLCS.getTranslation("auditor_warning");
                    break;
                case HTMLCS.NOTICE:
                    b = _global.HTMLCS.getTranslation("auditor_notice")
            }
            var c = "";
            a.element && (c = a.element.nodeName.toLowerCase());
            var d = "";
            a.element.id && "" !== a.element.id && (d = "#" + a.element.id);
            var e = "";
            if (a.element.outerHTML) {
                var f = a.element.cloneNode(!0);
                f.innerHTML = "...", e = f.outerHTML
            }
            console.log("[HTMLCS] " + b + "|" + a.code + "|" + c + "|" + d + "|" + a.msg + "|" + e)
        }
    };
    _global.HTMLCSAuditor = new function() {
        var a = "HTMLCS-",
            b = "",
            c = "",
            d = [],
            e = {},
            f = null,
            g = null,
            h = [],
            j = 1,
            k = null,
            l = this;
        this.pointerContainer = null;
        var m = function(a, b, c, d) {
                var e = f.createElement("div");
                e.id = a, e.className = "HTMLCS-button", e.setAttribute("title", c);
                var g = f.createElement("span");
                g.className = "HTMLCS-button-icon HTMLCS-button-" + b, e.appendChild(g);
                var h = f.createTextNode(String.fromCharCode(160));
                return e.appendChild(h), d instanceof Function == !0 && (e.onclick = function() {
                    !1 === /disabled/.test(e.className) && d(e)
                }), e
            },
            n = function(a, b, c, d, e) {
                void 0 === c && (c = !1);
                var g = f.createElement("label"),
                    h = "";
                g.className = "HTMLCS-checkbox", h += '<span class="HTMLCS-checkbox-switch">', h += '<span class="HTMLCS-checkbox-slider"></span>', h += '<input id="' + a + '" type="checkbox"', !0 === c && (h += ' checked="checked"', g.className += " active"), !0 === d && (h += ' disabled="disabled"', g.className += " disabled"), h += ' title="' + b + '" /></span>', g.innerHTML = h;
                var i = g.getElementsByTagName("input")[0];
                return g.onclick = function(a) {
                    return !1 === d && (i.checked = !i.checked, !0 === i.checked ? g.className += " active" : g.className = g.className.replace("active", ""), e instanceof Function == !0 && e(i)), !1
                }, g
            },
            o = function(a, b) {
                var c = f.createElement("div");
                c.className = "HTMLCS-header", c.innerHTML = "HTML_CodeSniffer by Squiz", c.setAttribute("title", _global.HTMLCS.getTranslation("auditor_using_standard") + a);
                var d = !1,
                    e = 0,
                    g = 0;
                c.onmousedown = function(a) {
                    return a = a || window.event, d = !0, e = a.clientX, g = a.clientY, !1
                }, f.onmousemove = function(a) {
                    if (a = a || window.event, !0 === d) {
                        var c = b.offsetTop,
                            f = b.offsetLeft;
                        g < a.clientY ? (c += a.clientY - g, b.style.top = c + "px") : g > a.clientY && (c -= g - a.clientY, b.style.top = c + "px"), e < a.clientX ? (f += a.clientX - e, b.style.left = f + "px") : e > a.clientX && (f -= e - a.clientX, b.style.left = f + "px"), e = a.clientX, g = a.clientY
                    }
                }, f.onmouseup = function(a) {
                    d = !1
                };
                var h = f.createElement("div");
                return h.className = "HTMLCS-close", h.setAttribute("title", _global.HTMLCS.getTranslation("auditor_close")), h.onmousedown = function() {
                    l.close.call(l)
                }, c.appendChild(h), c
            },
            p = function(a, b, g) {
                var h = f.createElement("div");
                h.className = "HTMLCS-summary";
                var i = f.createElement("div");
                i.className = "HTMLCS-summary-left", h.appendChild(i);
                var j = f.createElement("div");
                j.className = "HTMLCS-summary-right", h.appendChild(j);
                var k = [];
                if (a > 0) {
                    var m = _global.HTMLCS.getTranslation("auditor_errors");
                    1 === a && (m = _global.HTMLCS.getTranslation("auditor_error")), k.push("<strong>" + a + "</strong> " + m)
                }
                if (b > 0) {
                    var m = _global.HTMLCS.getTranslation("auditor_warnings");
                    1 === b && (m = _global.HTMLCS.getTranslation("auditor_warning")), k.push("<strong>" + b + "</strong> " + m)
                }
                if (g > 0) {
                    var m = _global.HTMLCS.getTranslation("auditor_notices");
                    1 === g && (m = _global.HTMLCS.getTranslation("auditor_notice")), k.push("<strong>" + g + "</strong> " + m)
                }
                var n = f.createElement("ol");
                n.className = "HTMLCS-lineage";
                var o = f.createElement("li");
                o.className = "HTMLCS-lineage-item";
                var p = f.createElement("a");
                p.className = "HTMLCS-lineage-link", p.href = "javascript:";
                var q = f.createElement("span");
                q.innerHTML = "Home", p.appendChild(q), p.onmousedown = function() {
                    l.run(c, d, e)
                };
                var r = f.createElement("li");
                return r.className = "HTMLCS-lineage-item", r.innerHTML = k.join(', &#160;<span class="HTMLCS-divider"></span>'), o.appendChild(p), n.appendChild(o), n.appendChild(r), i.appendChild(n), j.appendChild(f.createTextNode(String.fromCharCode(160))), h
            },
            q = function(a, b) {
                var g = f.createElement("div");
                g.className = "HTMLCS-summary-detail";
                var i = f.createElement("div");
                i.className = "HTMLCS-summary-left";
                var j = f.createElement("div");
                j.className = "HTMLCS-summary-right";
                var k = f.createElement("ol");
                k.className = "HTMLCS-lineage";
                var n = f.createElement("li");
                n.className = "HTMLCS-lineage-item";
                var o = f.createElement("a");
                o.className = "HTMLCS-lineage-link", o.href = "javascript:";
                var p = f.createElement("span");
                p.innerHTML = _global.HTMLCS.getTranslation("auditor_home"), o.appendChild(p), o.onmousedown = function() {
                    l.run(c, d, e)
                };
                var r = f.createElement("li");
                r.className = "HTMLCS-lineage-item";
                var s = f.createElement("a");
                s.className = "HTMLCS-lineage-link", s.href = "javascript:", s.innerHTML = _global.HTMLCS.getTranslation("auditor_report"), s.setAttribute("title", _global.HTMLCS.getTranslation("auditor_back_to_report")), s.onmousedown = function() {
                    var a = f.querySelectorAll(".HTMLCS-inner-wrapper")[0];
                    a.style.marginLeft = "0px", a.style.maxHeight = null, g.style.display = "none", f.querySelectorAll(".HTMLCS-summary")[0].style.display = "block"
                };
                var t = f.createElement("li");
                t.className = "HTMLCS-lineage-item", t.innerHTML = _global.HTMLCS.getTranslation("auditor_issue") + " " + a + " " + _global.HTMLCS.getTranslation("auditor_of") + " " + b, n.appendChild(o), r.appendChild(s), k.appendChild(n), k.appendChild(r), k.appendChild(t), i.appendChild(k);
                var u = f.createElement("div");
                u.className = "HTMLCS-button-group";
                var w = m("HTMLCS-button-previous-issue", "previous", _global.HTMLCS.getTranslation("auditor_previous_issue"), function(c) {
                        var d = Number(a) - 1;
                        if (d >= 1) {
                            v(d - 1), wrapper = g.parentNode;
                            var e = q(d, b);
                            wrapper.replaceChild(e, g), e.style.display = "block";
                            var h = f.querySelectorAll(".HTMLCS-issue-detail-list")[0];
                            h.firstChild.style.marginLeft = parseInt(h.firstChild.style.marginLeft, 10) + 300 + "px", y(d - 1)
                        }
                    }),
                    x = m("HTMLCS-button-next-issue", "next", _global.HTMLCS.getTranslation("auditor_next_issue"), function(c) {
                        var d = Number(a) + 1;
                        if (d <= h.length) {
                            v(d - 1), wrapper = g.parentNode;
                            var e = q(d, b);
                            wrapper.replaceChild(e, g), e.style.display = "block";
                            var i = f.querySelectorAll(".HTMLCS-issue-detail-list")[0];
                            i.firstChild.style.marginLeft = parseInt(i.firstChild.style.marginLeft, 10) - 300 + "px", y(d - 1)
                        }
                    });
                return 1 === a && (w.className += " disabled"), a === b && (x.className += " disabled"), u.appendChild(w), u.appendChild(x), j.appendChild(u), g.appendChild(i), g.appendChild(j), g
            },
            r = function(a) {
                var b = 300 * Math.ceil(a.length / 5),
                    c = f.createElement("div");
                c.id = "HTMLCS-issues", c.className = "HTMLCS-details", c.setAttribute("style", "width: " + b + "px");
                var d = f.createElement("ol");
                d.className = "HTMLCS-issue-list", d.setAttribute("style", "margin-left: 0");
                for (var e = 0; e < a.length; e++) {
                    if (e > 0 && e % 5 == 0) {
                        c.appendChild(d);
                        var d = f.createElement("ol");
                        d.className = "HTMLCS-issue-list"
                    }
                    var g = u(e, a[e]);
                    d.appendChild(g)
                }
                return c.appendChild(d), c
            },
            s = function(a) {
                var b = 300 * a.length,
                    c = f.createElement("div");
                c.id = "HTMLCS-issues-detail", c.className = "HTMLCS-details", c.setAttribute("style", "width: " + b + "px");
                var d = f.createElement("ol");
                d.className = "HTMLCS-issue-detail-list", d.setAttribute("style", "margin-left: 0");
                for (var e = 0; e < a.length; e++) {
                    var g = w(e, a[e]);
                    d.appendChild(g)
                }
                return c.appendChild(d), c
            },
            t = function() {
                var a = f.createElement("div");
                a.className = "HTMLCS-settings";
                var b = f.createElement("div");
                b.id = "HTMLCS-settings-use-standard";
                var g = f.createElement("label");
                g.innerHTML = _global.HTMLCS.getTranslation("auditor_standards") + ":", g.setAttribute("for", "HTMLCS-settings-use-standard-select");
                var i = f.createElement("select");
                i.id = "HTMLCS-settings-use-standard-select", i.innerHTML = "";
                for (var j = HTMLCSAuditor.getStandardList(), k = 0; k < j.length; k++) {
                    var m = j[k],
                        o = f.createElement("option");
                    o.value = m, o.innerHTML = _global["HTMLCS_" + m].name, m === c && (o.selected = !0), i.appendChild(o), i.onchange = function() {
                        c = this.options[this.selectedIndex].value, l.run(c, d, e)
                    }
                }
                var p = f.createElement("div");
                p.id = "HTMLCS-settings-issue-count";
                var q = f.createElement("div");
                q.id = "HTMLCS-settings-issue-count-help", q.innerHTML = _global.HTMLCS.getTranslation("auditor_select_types");
                var r = f.createElement("div");
                r.id = "HTMLCS-settings-view-report", r.innerHTML = _global.HTMLCS.getTranslation("auditor_view_report"), r.onclick = function() {
                    if (!1 === /disabled/.test(this.className)) {
                        e.show = {
                            error: f.getElementById("HTMLCS-include-error").checked,
                            warning: f.getElementById("HTMLCS-include-warning").checked,
                            notice: f.getElementById("HTMLCS-include-notice").checked
                        };
                        var a = f.getElementById("HTMLCS-wrapper"),
                            b = l.build(c, h, e);
                        e.parentElement ? e.parentElement.replaceChild(b, a) : (b.style.left = a.style.left, b.style.top = a.style.top, f.body.replaceChild(b, a)), e.listUpdateCallback && e.listUpdateCallback.call(this, h)
                    }
                };
                var s = (f.getElementById("HTMLCS-wrapper"), l.countIssues(h));
                void 0 === e.show && h.length > 0 && (e.show = {
                    error: !0,
                    warning: !0,
                    notice: !1
                }, 0 === s.error && 0 === s.warning && (e.show.notice = !0));
                for (var t in s) {
                    var u = s[t],
                        v = f.createElement("div");
                    v.className = "HTMLCS-issue-tile HTMLCS-" + t.toLowerCase();
                    var w = null,
                        x = f.createElement("div");
                    x.className = "HTMLCS-tile-text", "error" == t && (w = _global.HTMLCS.getTranslation("auditor_error"), 1 !== u && (w = _global.HTMLCS.getTranslation("auditor_errors"))), "warning" == t && (w = _global.HTMLCS.getTranslation("auditor_warning"), 1 !== u && (w = _global.HTMLCS.getTranslation("auditor_warnings"))), "notice" == t && (w = _global.HTMLCS.getTranslation("auditor_notice"), 1 !== u && (w = _global.HTMLCS.getTranslation("auditor_notices")));
                    var y = "<strong>" + u + "</strong> " + w;
                    if (x.innerHTML = y, void 0 === e.show) var z = !1,
                        A = !0;
                    else {
                        var z = e.show[t],
                            A = !1;
                        0 === u && (z = !1, A = !0)
                    }
                    var B = n("HTMLCS-include-" + t, "Toggle display of " + t + " messages", z, A, function(a) {
                        var b = !1;
                        !1 === f.getElementById("HTMLCS-include-error").disabled && (e.show.error = f.getElementById("HTMLCS-include-error").checked, b = b || e.show.error), !1 === f.getElementById("HTMLCS-include-warning").disabled && (e.show.warning = f.getElementById("HTMLCS-include-warning").checked, b = b || e.show.warning), !1 === f.getElementById("HTMLCS-include-notice").disabled && (e.show.notice = f.getElementById("HTMLCS-include-notice").checked, b = b || e.show.notice), !0 === b ? r.className = r.className.replace(/ disabled/g, "") : r.className += " disabled"
                    });
                    v.appendChild(x), v.appendChild(B), p.appendChild(v)
                }
                if (void 0 !== e.show) {
                    !1 === (e.show.error || e.show.warning || e.show.notice) && (r.className += " disabled")
                } else r.className += " disabled";
                return b.appendChild(g), b.appendChild(i), a.appendChild(b), a.appendChild(p), a.appendChild(q), a.appendChild(r), a
            },
            u = function(a, b) {
                var c = "",
                    d = "",
                    e = "";
                switch (b.type) {
                    case HTMLCS.ERROR:
                        d = "Error";
                        break;
                    case HTMLCS.WARNING:
                        d = "Warning";
                        break;
                    case HTMLCS.NOTICE:
                        d = "Notice"
                }
                var e = d.toLowerCase(),
                    g = b.msg;
                g.length > 115 && (g = g.substr(0, 115) + "...");
                var c = f.createElement("li");
                c.id = "HTMLCS-msg-" + a;
                var i = f.createElement("span");
                i.className = "HTMLCS-issue-type HTMLCS-" + e, i.setAttribute("title", d), c.appendChild(i);
                var j = f.createElement("span");
                return j.className = "HTMLCS-issue-title", j.innerHTML = g, c.appendChild(j), c.onclick = function() {
                    var a = this.id.replace(new RegExp("HTMLCS-msg-"), "");
                    v(a);
                    var b = f.querySelectorAll(".HTMLCS-issue-detail-list")[0];
                    b.className += " HTMLCS-transition-disabled", b.firstChild.style.marginLeft = -300 * a + "px", y(a), setTimeout(function() {
                        b.className = b.className.replace(new RegExp(" HTMLCS-transition-disabled"), "")
                    }, 500);
                    var c = f.querySelectorAll(".HTMLCS-inner-wrapper")[0];
                    c.style.marginLeft = "-300px", c.style.maxHeight = "15em", summary = f.querySelectorAll(".HTMLCS-summary-detail")[0];
                    var d = q(parseInt(a) + 1, h.length);
                    summary.parentNode.replaceChild(d, summary), d.style.display = "block", f.querySelectorAll(".HTMLCS-summary")[0].style.display = "none"
                }, c
            },
            v = function(a) {
                for (var b = f.querySelectorAll(".HTMLCS-issue-detail-list")[0], c = b.getElementsByTagName("li"), d = 0; d < c.length; d++) c[d].className = c[d].className.replace(new RegExp(" HTMLCS-current"), "");
                f.getElementById("HTMLCS-msg-detail-" + a).className += " HTMLCS-current", e.showIssueCallback && e.showIssueCallback.call(this, a)
            },
            w = function(b, d, h) {
                void 0 === h && (h = c);
                var i = "";
                switch (d.type) {
                    case HTMLCS.ERROR:
                        i = "Error";
                        break;
                    case HTMLCS.WARNING:
                        i = "Warning";
                        break;
                    case HTMLCS.NOTICE:
                        i = "Notice"
                }
                var j = a + i.toLowerCase(),
                    k = HTMLCS.util.getElementWindow(f)["HTMLCS_" + h],
                    k = g["HTMLCS_" + h],
                    n = [];
                k.getMsgInfo && (n = k.getMsgInfo(d.code));
                var o = f.createElement("li");
                o.id = "HTMLCS-msg-detail-" + b;
                var p = f.createElement("div");
                p.className = "HTMLCS-issue-details";
                var q = f.createElement("span");
                q.className = "HTMLCS-issue-type " + j, q.setAttribute("title", i);
                var r = f.createElement("div");
                r.className = "HTMLCS-issue-title", r.innerHTML = d.msg;
                var s = f.createElement("div");
                s.className = "HTMLCS-issue-wcag-ref";
                for (var t = "", u = 0; u < n.length; u++) t += "<em>" + n[u][0] + ":</em> " + n[u][1] + "<br/>";
                if (s.innerHTML = t, p.appendChild(q), p.appendChild(r), p.appendChild(s), o.appendChild(p), !1 === A.isPointable(d.element)) {
                    var v = f.createElement("div");
                    v.className = "HTMLCS-issue-source", o.appendChild(v);
                    var w = f.createElement("div");
                    w.className = "HTMLCS-issue-source-inner-u2p";
                    var x = _global.HTMLCS.getTranslation("auditor_unable_to_point");
                    if ("#document" === d.element.nodeName) x = _global.HTMLCS.getTranslation("auditor_applies_entire_document");
                    else if (null === d.element.ownerDocument) x = _global.HTMLCS.getTranslation("auditor_unable_to_point_removed");
                    else {
                        var y = d.element.ownerDocument.getElementsByTagName("body")[0];
                        !1 === HTMLCS.util.isInDocument(d.element) ? x += _global.HTMLCS.getTranslation("auditor_unable_to_point_entire") : !1 === HTMLCS.util.contains(y, d.element) ? x = _global.HTMLCS.getTranslation("auditor_unable_to_point_outside") : x += _global.HTMLCS.getTranslation("auditor_unable_to_point_outside")
                    }
                    void 0 !== w.textContent ? w.textContent = x : w.innerText = x, v.appendChild(w)
                }
                if (e.customIssueSource) {
                    var v = f.createElement("div");
                    v.className = "HTMLCS-issue-source", o.appendChild(v), e.customIssueSource.call(this, b, d, h, v, p)
                } else {
                    var v = f.createElement("div");
                    v.className = "HTMLCS-issue-source";
                    var z = f.createElement("div");
                    z.className = "HTMLCS-issue-source-header";
                    var B = f.createElement("strong");
                    B.innerHTML = _global.HTMLCS.getTranslation("auditor_code_snippet");
                    var C = m("HTMLCS-button-point-to-element-" + b, "pointer", _global.HTMLCS.getTranslation("auditor_point_to_element"), function() {
                        l.pointToElement(d.element)
                    });
                    if (z.appendChild(B), z.appendChild(C), v.appendChild(z), d.element.outerHTML) {
                        var D = "",
                            E = "";
                        if (d.element.innerHTML.length > 31) var F = d.element.outerHTML.replace(d.element.innerHTML, d.element.innerHTML.substr(0, 31) + "...");
                        else var F = d.element.outerHTML;
                        for (var G = d.element.previousSibling; D.length <= 31 && null !== G;) 1 === G.nodeType ? D = G.outerHTML : 3 === G.nodeType && (D = void 0 !== G.textContent ? G.textContent + D : G.nodeValue + D), D.length > 31 && (D = "..." + D.substr(D.length - 31)), G = G.previousSibling;
                        for (var H = d.element.nextSibling; E.length <= 31 && null !== H;) 1 === H.nodeType ? E += H.outerHTML : 3 === H.nodeType && (void 0 !== H.textContent ? E += H.textContent : E += H.nodeValue), E.length > 31 && (E = E.substr(0, 31) + "..."), H = H.nextSibling;
                        var w = f.createElement("div");
                        w.className = "HTMLCS-issue-source-inner";
                        var I = f.createElement("strong");
                        void 0 !== I.textContent ? I.textContent = F : I.innerText = F, w.appendChild(f.createTextNode(D)), w.appendChild(I), w.appendChild(f.createTextNode(E)), v.appendChild(w)
                    } else if ("#document" === d.element.nodeName);
                    else {
                        var w = f.createElement("div");
                        w.className = "HTMLCS-issue-source-not-supported";
                        var J = _global.HTMLCS.getTranslation("auditor_unsupported_browser");
                        w.appendChild(f.createTextNode(J)), v.appendChild(w)
                    }
                    o.appendChild(v)
                }
                return o
            },
            x = function(a, b) {
                var c = f.createElement("div");
                c.className = "HTMLCS-navigation";
                var d = f.createElement("span");
                d.className = "HTMLCS-nav-button HTMLCS-previous", d.innerHTML = String.fromCharCode(160), 1 === a && (d.className += " HTMLCS-disabled"), c.appendChild(d);
                var e = f.createElement("span");
                e.className = "HTMLCS-page-number", e.innerHTML = _global.HTMLCS.getTranslation("auditor_issue") + " " + a + " " + _global.HTMLCS.getTranslation("auditor_of") + " " + b, c.appendChild(e);
                var g = f.createElement("span");
                return g.className = "HTMLCS-nav-button HTMLCS-next", g.innerHTML = String.fromCharCode(160), a === b && (g.className += " HTMLCS-disabled"), c.appendChild(g), d.onclick = function() {
                    j > 1 && 1 === --j && (d.className += " HTMLCS-disabled"), b > 1 && (g.className = g.className.replace(new RegExp(" HTMLCS-disabled"), "")), e.innerHTML = "", e.appendChild(document.createTextNode(_global.HTMLCS.getTranslation("auditor_issue") + " " + j + " " + _global.HTMLCS.getTranslation("auditor_of") + " " + b)), f.querySelectorAll(".HTMLCS-issue-list")[0].style.marginLeft = -300 * (j - 1) + "px"
                }, g.onclick = function() {
                    j < b && ++j === b && (g.className += " HTMLCS-disabled"), b > 1 && (d.className = d.className.replace(new RegExp(" HTMLCS-disabled"), "")), e.innerHTML = "", e.appendChild(document.createTextNode(_global.HTMLCS.getTranslation("auditor_issue") + " " + j + " " + _global.HTMLCS.getTranslation("auditor_of") + " " + b)), f.querySelectorAll(".HTMLCS-issue-list")[0].style.marginLeft = -300 * (j - 1) + "px"
                }, c
            },
            y = function(a) {
                var b = h[Number(a)];
                if (b.element) {
                    var c = f.getElementById("HTMLCS-button-point-to-element-" + a);
                    if (A.container = l.pointerContainer || f.getElementById("HTMLCS-wrapper"), !1 === A.isPointable(b.element)) {
                        var d = A.getPointer(b.element);
                        A.pointer && (d.className += " HTMLCS-pointer-hidden"), c && (c.className += " disabled")
                    } else c && (c.className = c.className.replace(" disabled", "")), A.pointTo(b.element)
                }
            },
            z = function(a, b) {
                if (0 === a.length) return void b.call(this);
                var c = a.shift();
                HTMLCS.loadStandard(c, function() {
                    z(a, b)
                })
            };
        this.setOption = function(a, b) {
            e[a] = b
        }, this.getIssue = function(a) {
            return h[a]
        }, this.countIssues = function(a) {
            for (var b = {
                    error: 0,
                    warning: 0,
                    notice: 0
                }, c = 0; c < a.length; c++) switch (a[c].type) {
                case HTMLCS.ERROR:
                    b.error++;
                    break;
                case HTMLCS.WARNING:
                    b.warning++;
                    break;
                case HTMLCS.NOTICE:
                    b.notice++
            }
            return b
        }, this.build = function(a, b, c) {
            var d = null;
            if (f) var d = f.getElementById("HTMLCS-wrapper");
            for (var g = 0, i = 0, j = 0, k = 0; k < b.length; k++) {
                var l = !1;
                switch (b[k].type) {
                    case HTMLCS.ERROR:
                        !1 === e.show.error ? l = !0 : g++;
                        break;
                    case HTMLCS.WARNING:
                        !1 === e.show.warning ? l = !0 : i++;
                        break;
                    case HTMLCS.NOTICE:
                        !1 === e.show.notice ? l = !0 : j++
                }!0 === l && (b.splice(k, 1), k--)
            }
            h = b;
            for (var m = "", n = "", k = 0; k < b.length; k++) k % 5 == 0 && (m += '<ol class="HTMLCS-issue-list"', 0 === k && (m += 'style="margin-left: 0em"'), m += ">"), m += u(k, b[k]), k % 5 != 4 && k !== b.length - 1 || (m += "</ol>"), n += w(k, b[k], a);
            var d = f.createElement("div");
            if (d.id = "HTMLCS-wrapper", d.className = "showing-issue-list", !0 !== e.noHeader) {
                var t = o(a, d);
                d.appendChild(t)
            }
            var v = p(g, i, j),
                y = q(1, b.length),
                z = f.createElement("div");
            z.id = "HTMLCS-issues-wrapper", z.className = "HTMLCS-inner-wrapper";
            var A = r(b);
            z.appendChild(A);
            var B = Math.ceil(b.length / 5),
                C = x(1, B);
            z.appendChild(C);
            var D = f.createElement("div");
            D.className = "HTMLCS-outer-wrapper", D.appendChild(z);
            var z = f.createElement("div");
            z.id = "HTMLCS-issues-detail-wrapper", z.className = "HTMLCS-inner-wrapper";
            var E = s(b);
            return z.appendChild(E), D.appendChild(z), d.appendChild(v), d.appendChild(y), d.appendChild(D), d
        }, this.buildSummaryPage = function() {
            var a = f.createElement("div");
            if (a.id = "HTMLCS-wrapper", a.className = "showing-settings", !0 !== e.noHeader) {
                var b = o(c, a);
                a.appendChild(b)
            }
            var d = t();
            return a.appendChild(d), a
        }, this.changeScreen = function(a) {
            var c = f.getElementById("HTMLCS-wrapper");
            c.className = c.className.replace(new RegExp("showing-" + b), ""), c.className += " showing-" + a, c.className = c.className.replace(/\s+/, " "), b = a
        }, this.includeCss = function(a, b) {
            if (!1 !== e.includeCss) {
                void 0 === b && (b = f);
                for (var c = b.querySelector("head"), d = c.getElementsByTagName("link"), g = !1, h = 0; h < d.length; h++)
                    if (!0 === new RegExp(a + ".css").test(d[h].getAttribute("href"))) {
                        g = !0;
                        break
                    } if (!1 === g) {
                    var i = b.createElement("link");
                    i.rel = "stylesheet", i.type = "text/css", i.href = e.path + a + ".css", c.appendChild(i)
                }
            }
        }, this.getStandardList = function() {
            var a = /^HTMLCS_[^_]+$/,
                b = [];
            for (i in window)
                if (!0 === a.test(i)) {
                    var c = window[i];
                    c.sniffs && c.name && b.push(i.substr(7))
                } return b
        }, this.getParentElement = function() {
            var a = null;
            if (e.parentElement) a = e.parentElement;
            else if (g.frames.length > 0) {
                for (var b = -1, c = null, d = 0; d < g.frames.length; d++) try {
                    if ("frame" === window.frames[d].frameElement.nodeName.toLowerCase() && window.frames[d].document) {
                        var f = window.frames[d].innerWidth * window.frames[d].innerHeight;
                        f > b && (b = f, c = window.frames[d].document.body)
                    }
                } catch (a) {}
                a = null === c ? document.body : c
            } else a = document.body;
            return a
        }, this.getOwnerDocument = function() {
            var a = this.getParentElement();
            return a.ownerDocument && (a = a.ownerDocument), a
        }, this.getDocumentLanguage = function() {
            var a = this.getOwnerDocument(),
                b = a.getElementsByTagName("html")[0];
            if (b) {
                var c = b.getAttribute("lang");
                if (c) return c
            }
            return "en"
        }, this.run = function(a, i, k) {
            g = window;
            for (var m = this.getStandardList(), n = [], o = 0; o < m.length; o++) _global["HTMLCS_" + m[o]] || n.push(m[o]);
            if (n.length > 0) return void z(n, function() {
                l.run(a, i, k)
            });
            if (null === i || void 0 === i) {
                if (i = [], 0 === document.querySelectorAll("frameset").length && i.push(document), g.frames.length > 0)
                    for (var o = 0; o < g.frames.length; o++) try {
                        i.push(g.frames[o].document)
                    } catch (a) {}
            } else if (i.nodeName)
                if ("input" === i.nodeName.toLowerCase())
                    if (!1 === i.hasAttribute("type")) i = i.value;
                    else {
                        var p = i.getAttribute("type").toLowerCase();
                        "text" === p && (i = i.value)
                    }
            else "textarea" === i.nodeName.toLowerCase() && (i = i.value);
            i instanceof Array == !1 && (i = [i]), void 0 === k && (k = {}), c = a, d = i, e = k, j = 1, b = "", h = [];
            var q = this.getParentElement();
            f = this.getOwnerDocument(), e.path || (e.path = "./"), k.lang || (e.lang = this.getDocumentLanguage()), void 0 === e.includeCss && (e.includeCss = !0), void 0 === e.ignoreMsgCodes && (e.ignoreMsgCodes = []), this.includeCss("HTMLCS");
            var r = f.getElementById("HTMLCS-wrapper"),
                s = !1,
                t = l.buildSummaryPage();
            t.className += " HTMLCS-processing", r ? (t.style.left = r.style.left, t.style.top = r.style.top, q.replaceChild(t, r)) : (e.openCallback && e.openCallback.call(this), s = !0, q.appendChild(t));
            var u = function() {
                    for (var a = 0; a < h.length; a++) {
                        var b = !1;
                        t && (t === h[a].element ? b = !0 : h[a].element.documentElement ? b = !1 : t.contains && !0 === t.contains(h[a].element) ? b = !0 : t.compareDocumentPosition && (16 & t.compareDocumentPosition(h[a].element)) > 0 && (b = !0));
                        for (var c = 0; c < k.ignoreMsgCodes.length; c++)
                            if (!0 === new RegExp(k.ignoreMsgCodes[c]).test(h[a].code)) {
                                b = !0;
                                break
                            }! 0 === b && (h.splice(a, 1), a--)
                    }
                    if (e.runCallback) {
                        var d = e.runCallback.call(this, h, s);
                        d instanceof Array == !0 && (h = d)
                    }
                    setTimeout(function() {
                        var a = f.getElementById("HTMLCS-wrapper"),
                            b = l.buildSummaryPage();
                        b.style.left = a.style.left, b.style.top = a.style.top, q.replaceChild(b, a)
                    }, 400)
                },
                v = function(a, b) {
                    for (var c = b.shift(); !c;) {
                        if (0 === b.length) return void u();
                        c = b.shift()
                    }
                    HTMLCS.process(a, c, function() {
                        h = h.concat(HTMLCS.getMessages()), 0 === b.length ? u() : v(a, b)
                    }, function() {}, k.lang)
                };
            v(a, d.concat([]))
        }, this.versionCheck = function(a) {
            if (a && null !== a.currentVersion && a.newVersion > a.currentVersion) {
                var b = f.createElement("div");
                b.id = "HTMLCS-settings-updated-notification", f.documentElement.querySelector(".HTMLCS-settings").appendChild(b);
                var c = "HTML_CodeSniffer has been updated to version " + a.newVersion + ".";
                c += ' <a href="http://squizlabs.github.io/HTML_CodeSniffer/patches/' + a.newVersion + '">View the changelog</a>', b.innerHTML = c
            }
        }, this.close = function() {
            if (f) {
                var a = f.getElementById("HTMLCS-wrapper");
                if (a) {
                    var b = A.getPointer(a);
                    b && b.parentNode && b.parentNode.removeChild(b), a.parentNode.removeChild(a), e.closeCallback && (h = e.closeCallback.call(this))
                }
            }
        }, this.pointToElement = function(a) {
            A.container = l.pointerContainer || f.getElementById("HTMLCS-wrapper"), A.pointTo(a)
        }, this.getCurrentStandard = function() {
            return c
        };
        var A = {
            pointerDim: {},
            container: null,
            getBoundingRectangle: function(a) {
                if (!a) return null;
                var b = this.getElementCoords(a),
                    c = this.getElementDimensions(a);
                return {
                    x1: b.x,
                    y1: b.y,
                    x2: b.x + c.width,
                    y2: b.y + c.height
                }
            },
            getElementDimensions: function(a) {
                return {
                    width: a.offsetWidth,
                    height: a.offsetHeight
                }
            },
            getElementCoords: function(a, b) {
                var c = 0,
                    d = 0,
                    e = HTMLCS.util.getElementWindow(a);
                if (!0 === b) var f = e.top;
                else var f = e;
                for (;;) {
                    do {
                        c += a.offsetLeft, d += a.offsetTop
                    } while (a = a.offsetParent);
                    if (e === f) break;
                    if (a = e.frameElement, e = e.parent, "frame" === a.nodeName.toLowerCase()) break
                }
                return {
                    x: c,
                    y: d
                }
            },
            getWindowDimensions: function(a) {
                var b = HTMLCS.util.getElementWindow(a),
                    c = a.ownerDocument,
                    d = 0,
                    e = 0;
                if (b.innerWidth) {
                    d = b.innerWidth, e = b.innerHeight;
                    var f = this.getScrollbarWidth(a);
                    c.documentElement.scrollHeight > e && "number" == typeof f && (d -= f), c.body.scrollWidth > d && "number" == typeof f && (e -= f)
                } else c.documentElement && (c.documentElement.clientWidth || c.documentElement.clientHeight) ? (d = c.documentElement.clientWidth, e = c.documentElement.clientHeight) : c.body && (c.body.clientWidth || c.body.clientHeight) && (d = c.body.clientWidth, e = c.body.clientHeight);
                return {
                    width: d,
                    height: e
                }
            },
            getScrollbarWidth: function(a) {
                if (null !== k) return k;
                doc = a.ownerDocument;
                var b = null,
                    c = null,
                    d = 0,
                    e = 0;
                b = doc.createElement("div"), b.style.position = "absolute", b.style.top = "-1000px", b.style.left = "-1000px", b.style.width = "100px", b.style.height = "50px", b.style.overflow = "hidden", c = doc.createElement("div"), c.style.width = "100%", c.style.height = "200px", b.appendChild(c), f.body.appendChild(b), d = c.offsetWidth, b.style.overflow = "auto", e = c.offsetWidth, doc.body.removeChild(doc.body.lastChild);
                var g = d - e;
                return k = g, g
            },
            getScrollCoords: function(a) {
                var b = HTMLCS.util.getElementWindow(a);
                doc = a.ownerDocument;
                var c = 0,
                    d = 0;
                return b.pageYOffset ? (c = b.pageXOffset, d = b.pageYOffset) : doc.body && (doc.body.scrollLeft || doc.body.scrollTop) ? (c = doc.body.scrollLeft, d = doc.body.scrollTop) : (c = doc.documentElement.scrollLeft, d = doc.documentElement.scrollTop), {
                    x: c,
                    y: d
                }
            },
            isPointable: function(a) {
                if (null === a.ownerDocument) return !1;
                for (var b = a.parentNode; b && b.ownerDocument;) b = b.parentNode;
                return null !== b && (!0 !== HTMLCS.util.isVisuallyHidden(a) && null !== this.getPointerDirection(a))
            },
            getPointerDirection: function(a) {
                var b = null,
                    c = this.getBoundingRectangle(a),
                    d = this.getPointer(a),
                    e = a.ownerDocument;
                d.className = d.className.replace("HTMLCS-pointer-hidden", ""), d.className += " HTMLCS-pointer-hidden-block", this.pointerDim.height = 62, this.pointerDim.width = 62;
                var f = this.getWindowDimensions(a),
                    g = (HTMLCS.util.getElementWindow(a), Math.max(0, Math.min(c.y1 - 100, e.documentElement.offsetHeight - f.height)));
                return c.y1 - this.pointerDim.height - 20 > g ? b = "down" : c.y2 + this.pointerDim.height < f.height - g ? b = "up" : c.x2 + this.pointerDim.width < f.width ? b = "left" : c.x1 - this.pointerDim.width > 0 && (b = "right"), d.className = d.className.replace("HTMLCS-pointer-hidden-block", ""), d.className += " HTMLCS-pointer-hidden", b
            },
            pointTo: function(a) {
                if (a.ownerDocument) var b = a.ownerDocument;
                else var b = a;
                var c = b.getElementById("HTMLCS-pointer");
                if (c && c.parentNode.removeChild(c), !1 !== this.isPointable(a)) {
                    var d = HTMLCS.util.getElementWindow(a).top,
                        e = (this.getWindowDimensions(d.document.documentElement), this.getPointerDirection(a)),
                        f = this.getPointer(a);
                    if (f.className = f.className.replace("HTMLCS-pointer-hidden-block", ""), null === e) f.className += " HTMLCS-pointer-hidden";
                    else {
                        var g = !1;
                        if ("fixed" === HTMLCS.util.style(a).position) var g = !0;
                        for (var h = a.parentNode; h.ownerDocument;) {
                            if ("fixed" === HTMLCS.util.style(h).position) {
                                g = !0;
                                break
                            }
                            h = h.parentNode
                        }
                        if (!0 === g) f.style.position = "fixed";
                        else {
                            f.style.position = "absolute";
                            for (var i = this.getElementCoords(a, !0), j = HTMLCS.util.getElementWindow(a), k = Math.max(i.y - 100, 0); k >= 0;) {
                                j.scrollTo(0, k);
                                if (k -= this.getScrollCoords(j.document.documentElement).y, k = Math.max(k, 0), j === d) break;
                                j = j.parent
                            }
                        }
                        this.showPointer(a, e)
                    }
                }
            },
            getPointer: function(a) {
                try {
                    var b = a.ownerDocument;
                    HTMLCSAuditor.includeCss("HTMLCS", b);
                    var c = "HTMLCS",
                        d = b.getElementById(c + "-pointer");
                    d || (d = b.createElement("div"), d.id = c + "-pointer", d.className = c + "-pointer " + c + "-pointer-hidden", b.body.appendChild(d))
                } catch (a) {}
                return d
            },
            showPointer: function(a, b) {
                var c = "HTMLCS",
                    d = this.getPointer(a);
                this._removeDirectionClasses(d), d.className += " " + c + "-pointer-" + b, d.className = d.className.replace(c + "-pointer-hidden", "");
                var e = this.getBoundingRectangle(a),
                    f = 0,
                    g = 0,
                    h = 20;
                switch (b) {
                    case "up":
                        h = -h, f = e.y2, g = e.x2 - e.x1 < 250 ? this.getRectMidPnt(e) - this.pointerDim.width / 2 : e.x1;
                        break;
                    case "down":
                    default:
                        f = e.y1 - this.pointerDim.height, g = e.x2 - e.x1 < 250 ? this.getRectMidPnt(e) - this.pointerDim.width / 2 : e.x1;
                        break;
                    case "left":
                        g = e.x2, f = this.getRectMidPnt(e, !0) - this.pointerDim.height / 2;
                        break;
                    case "right":
                        h = -h, g = e.x1 - this.pointerDim.width, f = this.getRectMidPnt(e, !0) - this.pointerDim.height / 2
                }
                var i = this.getScrollCoords(a);
                d.style.top = f + "px", d.style.left = g + "px";
                var j = this.getBoundingRectangle(this.container);
                e = this.getBoundingRectangle(d);
                var k = e.x1 + (e.x2 - e.x1) / 2,
                    l = e.y1 + (e.y2 - e.y1) / 2;
                if ("fixed" !== HTMLCS.util.style(d).position && (l -= i.y), j.x1 <= k && j.x2 >= k && j.y1 <= l && j.y2 >= l) {
                    var m = this;
                    this.container.className += " HTMLCS-translucent", setTimeout(function() {
                        m.container.className = m.container.className.replace("HTMLCS-translucent", "")
                    }, 4e3)
                }
                this.bounce(d, function() {
                    setTimeout(function() {
                        d.parentNode && d.parentNode.removeChild(d)
                    }, 1500)
                }, b)
            },
            bounce: function(a, b, c) {
                var d = c,
                    e = 0,
                    f = "",
                    g = 0;
                switch (c) {
                    case "up":
                        d = c + "-op", g = 30;
                    case "down":
                        f = "top";
                        break;
                    case "left":
                        d = c + "-op", g = 30;
                    case "right":
                        f = "left"
                }
                e = Number(a.style[f].replace("px", "")) + g;
                var h = e,
                    i = e - 30,
                    j = 0,
                    k = setInterval(function() {
                        if (d === c) {
                            if (h--, a.style[f] = h + "px", h < i && (d = c + "-op", 5 === j && 0 !== g)) return clearInterval(k), void b.call(this)
                        } else if (h++, a.style[f] = h + "px", h >= e && (d = c, 5 === ++j && 0 === g)) return clearInterval(k), void b.call(this)
                    }, 10)
            },
            getRectMidPnt: function(a, b) {
                return !0 === b ? a.y1 + (a.y2 - a.y1) / 2 : a.x1 + (a.x2 - a.x1) / 2
            },
            _removeDirectionClasses: function(a) {
                for (var b = ["down", "up", "left", "right"], c = b.length, d = 0; d < c; d++) a.className = a.className.replace("HTMLCS-pointer-" + b[d], "")
            }
        }
    }; // Expose globals.
    return _global;
}));