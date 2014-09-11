/*!
 * jQuery corner plugin: simple corner rounding
 * Examples and documentation at: http://jquery.malsup.com/corner/
 * version 2.09 (11-MAR-2010)
 * Requires jQuery v1.3.2 or later
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 * Authors: Dave Methvin and Mike Alsup
 */

/**
 *  corner() takes a single string argument:  $('#myDiv').corner("effect corners width")
 *
 *  effect:  name of the effect to apply, such as round, bevel, notch, bite, etc (default is round). 
 *  corners: one or more of: top, bottom, tr, tl, br, or bl.  (default is all corners)
 *  width:   width of the effect; in the case of rounded corners this is the radius. 
 *           specify this value using the px suffix such as 10px (yes, it must be pixels).
 */
;(function($) { 

var style = document.createElement('div').style;
var moz = style['MozBorderRadius'] !== undefined;
var webkit = style['WebkitBorderRadius'] !== undefined;
var radius = style['borderRadius'] !== undefined || style['BorderRadius'] !== undefined;
var mode = document.documentMode || 0;
var noBottomFold = $.browser.msie && (($.browser.version < 8 && !mode) || mode < 8);

var expr = $.browser.msie && (function() {
    var div = document.createElement('div');
    try { div.style.setExpression('width','0+0'); div.style.removeExpression('width'); }
    catch(e) { return false; }
    return true;
})();
    
function sz(el, p) { 
    return parseInt($.css(el,p))||0; 
};
function hex2(s) {
    var s = parseInt(s).toString(16);
    return ( s.length < 2 ) ? '0'+s : s;
};
function gpc(node) {
    while(node) {
        var v = $.css(node,'backgroundColor');
        if (v && v != 'transparent' && v != 'rgba(0, 0, 0, 0)') {
	        if (v.indexOf('rgb') >= 0) { 
	            var rgb = v.match(/\d+/g); 
	            return '#'+ hex2(rgb[0]) + hex2(rgb[1]) + hex2(rgb[2]);
	        }
            return v;
		}
		node = node.parentNode; // keep walking if transparent
    }
    return '#ffffff';
};

function getWidth(fx, i, width) {
    switch(fx) {
    case 'round':  return Math.round(width*(1-Math.cos(Math.asin(i/width))));
    case 'cool':   return Math.round(width*(1+Math.cos(Math.asin(i/width))));
    case 'sharp':  return Math.round(width*(1-Math.cos(Math.acos(i/width))));
    case 'bite':   return Math.round(width*(Math.cos(Math.asin((width-i-1)/width))));
    case 'slide':  return Math.round(width*(Math.atan2(i,width/i)));
    case 'jut':    return Math.round(width*(Math.atan2(width,(width-i-1))));
    case 'curl':   return Math.round(width*(Math.atan(i)));
    case 'tear':   return Math.round(width*(Math.cos(i)));
    case 'wicked': return Math.round(width*(Math.tan(i)));
    case 'long':   return Math.round(width*(Math.sqrt(i)));
    case 'sculpt': return Math.round(width*(Math.log((width-i-1),width)));
	case 'dogfold':
    case 'dog':    return (i&1) ? (i+1) : width;
    case 'dog2':   return (i&2) ? (i+1) : width;
    case 'dog3':   return (i&3) ? (i+1) : width;
    case 'fray':   return (i%2)*width;
    case 'notch':  return width; 
	case 'bevelfold':
    case 'bevel':  return i+1;
    }
};

$.fn.corner = function(options) {
    // in 1.3+ we can fix mistakes with the ready state
	if (this.length == 0) {
        if (!$.isReady && this.selector) {
            var s = this.selector, c = this.context;
            $(function() {
                $(s,c).corner(options);
            });
        }
        return this;
	}

    return this.each(function(index){
		var $this = $(this);
		// meta values override options
		var o = [$this.attr($.fn.corner.defaults.metaAttr) || '', options || ''].join(' ').toLowerCase();
		var keep = /keep/.test(o);                       // keep borders?
		var cc = ((o.match(/cc:(#[0-9a-f]+)/)||[])[1]);  // corner color
		var sc = ((o.match(/sc:(#[0-9a-f]+)/)||[])[1]);  // strip color
		var width = parseInt((o.match(/(\d+)px/)||[])[1]) || 10; // corner width
		var re = /round|bevelfold|bevel|notch|bite|cool|sharp